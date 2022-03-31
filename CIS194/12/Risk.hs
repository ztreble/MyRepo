module Risk where
import Control.Monad (replicateM)

import Control.Monad.Random

------------------------------------------------------------
-- Die values

newtype DieValue = DV { unDV :: Int } 
  deriving (Eq, Ord, Show, Num)

first :: (a -> b) -> (a, c) -> (b, c)
first f (a, c) = (f a, c)

instance Random DieValue where
  random           = first DV . randomR (1,6)
  randomR (low,hi) = first DV . randomR (max 1 (unDV low), min 6 (unDV hi))

die :: Rand StdGen DieValue
die = getRandom

dice :: Int -> Rand StdGen [DieValue]
dice n = replicateM n die

------------------------------------------------------------
-- Risk

type Army = Int

data Battlefield = Battlefield { attackers :: Army, defenders :: Army }
    deriving Show

exactSuccessProb :: Battlefield -> Double
exactSuccessProb bf = 0

succesProb :: Battlefield -> Rand StdGen Double
succesProb bf = replicateM 1000 (invade bf) >>= success

success :: [Battlefield] -> Rand StdGen Double 
success bfs = return $ fromIntegral (length x)  / fromIntegral (length bfs)
  where x = filter ((== 0) . defenders) bfs

invade :: Battlefield -> Rand StdGen Battlefield
invade bf
    | attackers bf <2 || defenders bf <= 0  = return bf
    | otherwise = battle bf >>= invade 

battle :: Battlefield -> Rand StdGen Battlefield
battle bf = dice (att+def) >>= \dc ->
            return (remArmy bf (battleOutcome (att, def) dc))
            where (att, def) = getTroops bf

getTroops :: Battlefield -> (Army, Army)
getTroops (Battlefield att def) = (attTroop, defTroop)
  where attTroop = if att >= 4 then 3 else att-1
        defTroop = if def >= 2 then 2 else def

remArmy :: Battlefield -> (Army, Army) -> Battlefield
remArmy (Battlefield att def) (lostAtt, lostDef)
  = Battlefield (att - lostAtt) (def - lostDef)

battleOutcome :: (Army, Army) -> [DieValue] -> (Army, Army)
battleOutcome (att, def) dc = compareDice sortedDice (0, 0)
  where sortedDice = fmapPair (reverse . sort) (splitAt att dc)

fmapPair :: (a -> b) -> (a, a) -> (b, b)
fmapPair f (a, b) = (f a, f b)

compareDice :: ([DieValue], [DieValue]) -> (Army, Army) -> (Army, Army)
compareDice ([], _) troops = troops
compareDice (_, []) troops = troops
compareDice (a:as, d:ds) (attL, defL)
  | a > d     = compareDice (as, ds) (attL, defL+1)
  | otherwise = compareDice (as, ds) (attL+1, defL)