#ifndef MOVE_H
#define MOVE_H
#include "missile.h"
#include "config.h"
#include <QPixmap>

class Move
{
public:
    Move();
    //更新舰艇坐标
    void updatePosition();
    //设置飞机位置

    void shoot();
    void shoot1();
    void shoot2();
    void shoot3();
    void shoot4();
    void shoot5();
    void shoot6();
    int flag[5];
public:
    //舰艇资源对象
    QPixmap m_Jianting;

    QPixmap Plane;
    QPixmap Plane1;
    QPixmap Plane2;
    QPixmap Plane3;
    QPixmap Plane4;
    //子弹坐标
    double m_XJianting;
    double m_YJianting;
    double m_Speed_Jianting;
    double XPlane;
    double YPlane;

    double XPlane1;
    double YPlane1;

    double XPlane2;
    double YPlane2;

    double XPlane3;
    double YPlane3;

    double XPlane4;
    double YPlane4;

    //子弹是否闲置
    bool m_Free;
    Missile  missile;
    Missile missile1;
    Missile  missile2;
    Missile  missile3;
    Missile  missile4;
    Missile m_lanjiedan1;
     Missile m_lanjiedan2;
    int m_Plane_Free=0;
    //发射间隔记录
    double m_recorder=0;
    //子弹的矩形边框（用于碰撞检测）
    QRect m_Rect;

    int jianting_life;
};

#endif // MOVE_H
