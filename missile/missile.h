#ifndef MISSILE_H
#define MISSILE_H
#include "config.h"
#include <QPixmap>

class Missile
{
public:
    Missile();
    //更新导弹坐标
    void updatePosition();
    void returnPosition();


public:
    //导弹资源对象
    QPixmap m_Missile;
    QPixmap m_Lanjiedan;

    int t;
    double Acceleration;
    int jug[4];

    int Mv_x[4];
    int Mv_y[4];

    int mv_x[2];
    int mv_y[2];
    //导弹坐标
    int m_X;
    double m_Y;
    int m_X1;
    double m_Y1;
    int m_X2;
    double m_Y2;
    int m_X3;
    double m_Y3;
    int m_X4;
    double m_Y4;
    //拦截弹坐标
    double m_x1;
    double m_y1;
    double m_x2;
    double m_y2;

    //导弹打到僵尸
    int m_hit;
    //导是否闲置
    bool m_Free;
    //导弹的矩形边框（用于碰撞检测）
    QRect m_Rect;
};

#endif // MISSILE_H
