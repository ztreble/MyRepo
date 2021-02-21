#ifndef MAINSCENE_H
#define MAINSCENE_H

#include <QWidget>
#include <QIcon>
#include <QTimer>
#include <QPainter>
#include "map.h"
#include "move.h"
#include "missile.h"
#include "bomb.h"
#include<QPalette>
#include<QBrush>
#include<QFont>
#include<QDialog>
#include<QPushButton>
class MainScene : public QWidget
{
    Q_OBJECT
public:
    MainScene(QWidget *parent = nullptr);
    ~MainScene();
    //初始化场景
    void initScene();
    //启动游戏
    void playGame();
    //更新坐标
    void updatePosition();
   // void ReturnPosition();
    //绘制到屏幕中
    void paintEvent(QPaintEvent *event);
    //定时器
    QTimer m_Timer;
    void collisionDetection();
    double time=0;
    char ptr[20];
    bool bump[5];
    bool lanjiedan_bomb[2];
    //地图,舰艇对象
    Map m_map;
    Move m_jianting;
    Move m_feiji;
    Move m_feiji1;
    Move m_feiji2;
    Move m_feiji3;
    Move m_feiji4;
    QPixmap JianTingBomb;
    Missile m_missile;
    Missile m_missile1;
    Missile m_missile2;
    Missile m_missile3;
    Missile m_missile4;

    Missile m_lanjiedan1;
    Missile m_lanjiedan2;
    //爆炸数组
    Bomb m_bombs[BOMB_NUM+5];
    int lasttime;
    int lasttime_lanjie_1;
    int lasttime_lanjie_2;
private:
    QPushButton *MainButton_1;
    QPushButton *MainButton_2;

};
#endif // MAINSCENE_H
