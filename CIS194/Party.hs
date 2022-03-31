{-# OPTIONS_GHC -fno-warn-orphans #-}
module Party where
import Employee 
import Data.Tree
import Data.Monoid
--- 1.1

-- | Adds Employee to the GuestList and update cached Fun score.
glCons :: Employee -> GuestList -> GuestList
-- stupid
-- glCons (Emp {empName = eN,empFun = ef}) (GL lst gf) = GL ((Emp {empName = eN,empFun = ef}):lst) (ef+gf)
glCons emp@(Emp {empFun = ef}) (GL lst gf) = GL (emp:lst) (ef+gf)

--- 1.2

instance Semigroup GuestList where 
    (GL al af) <> (GL bl bf) = GL (al++bl) (af+bf)

instance  Monoid GuestList where
    mempty = GL [] 0

--- 1.3

moreFun :: GuestList -> GuestList -> GuestList
moreFun gl1@(GL gp1 gf1) gl2@(GL gp2 gf2) 
    |gf1>gf2    =   gl1
    |otherwise  =   gl2

--- 2
-- data Tree a = Node {
-- rootLabel :: a, -- label value
-- subForest :: [Tree a] -- zero or more child trees
-- }

treeFold :: (a -> [b] -> b) -> b -> Tree a -> b
treeFold f init (Node {rootLabel = rl, subForest = sf})
  = f rl (map (treeFold f init) sf)

-- | First part of list is with boss.
nextLevel :: Employee -> [(GuestList, GuestList)] -> (GuestList, GuestList)
nextLevel boss bestLists = (maximumS withBossL, maximumS withoutBossL)
  where withoutBossL   = map fst bestLists
        -- ^ The new withoutBossList has sub bosses in it.

        withoutSubBoss = map snd bestLists
        withBossL      = map (glCons boss) withoutSubBoss
        -- ^ The new withBossList doesn't have sub bosses in it.

maximumS ::(Monoid a, Ord a) => [a] -> a
maximumS [] = mempty
maximumS lst = maximum lst

maxFun :: Tree Employee -> GuestList
maxFun tree = uncurry max res
  where res = treeFold nextLevel (mempty, mempty) tree
