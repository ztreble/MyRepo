#include "missile.h"
#include "move.h"
#include "QDebug"
#include "mainscene.h"
#include "mainscene.cpp"
Missile::Missile()
{
    //加载子弹资源
    m_Missile.load(MISSILE);
    m_Lanjiedan.load(LANJIEDAN);
    m_x1=600;
    m_x2=700;
    m_y1=622;
    m_y2=500;
    //子弹状态
    m_X = 0;
    m_X1 = 0;
    m_X2 = 0;
    m_X3 = 0;
    m_X4 = 0;
    m_Free = true;
    Acceleration = 0.0153483233300487;
    t = 0;

    //子弹矩形框
    m_Rect.setWidth(m_Missile.width());
    m_Rect.setHeight(m_Missile.height());
    m_Rect.moveTo(m_X,m_Y);
    m_Rect.moveTo(m_X1,m_Y1);
    m_Rect.moveTo(m_X2,m_Y2);
    m_Rect.moveTo(m_X3,m_Y3);
    m_Rect.moveTo(m_X4,m_Y4);

    m_Rect.setWidth(m_Lanjiedan.width());
    m_Rect.setHeight(m_Lanjiedan.height());
    m_Rect.moveTo(m_x1,m_y1);
    m_Rect.moveTo(m_x2,m_y2);
}
void Missile::updatePosition()
{
    t++;

    m_X+=3;
    m_Y +=Acceleration*(2*t+1)/2;

    m_X1+=3;
//    m_Y1 += 1.4;
    m_Y1 +=Acceleration*(2*t+1)/2;

//    if(m_X2<=700){
//        m_Y2 += 2;
        m_Y2 +=Acceleration*(2*t+1)/2;
        m_X2+=3;
//    }

    m_X3+=3;
//    m_Y3 += 1.45;
    m_Y3 +=Acceleration*(2*t+1)/2;

//    if(m_X4<=680){
//        m_Y4 += 2.5;
    if(m_X4<=860){
        m_Y4 +=Acceleration*(2*t+1)/2;
        m_X4 += 3;
    }else{
        m_Y4 +=Acceleration*(2*t+1)/2;
        m_X4 -= 3;
    }



    if(m_x1>600 ){
        m_x1-=3;
        m_y1-=1.54;
    }
    if(m_x2>650){
        m_x2-=2;
        m_y2-=1.45;
    }
}

void Missile::returnPosition()
{

}
