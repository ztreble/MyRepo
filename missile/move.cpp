#include "move.h"
#include "QDebug"
Move::Move()
{

    int flag[5];
    //加载舰艇资源
    QSize picSize(600,400);
    m_Jianting.load(JIANTING);

    m_XJianting=1110;
    m_YJianting=520;
    Plane.load(FEIJI);
    Plane1.load(FEIJI);
    Plane2.load(FEIJI);
    Plane3.load(FEIJI);
    Plane4.load(FEIJI);
   /*Plane.load(PLANE);
    Plane1.load(PLANE);
    Plane2.load(PLANE);
    Plane3.load(PLANE);
    Plane4.load(PLANE);*/
    XPlane=10;
    YPlane=300;
    XPlane1=50;
    YPlane1=300;
    XPlane2=90;
    YPlane2=300;
    XPlane3=130;
    YPlane3=300;
    XPlane4=170;
    YPlane4=300;
    missile.m_Free=1;
    missile1.m_Free=1;
    jianting_life=1500;

    //子弹状态
    m_Free = true;
    //舰艇速度
    //m_Speed_Jianting= 2;

    m_Rect.setWidth( Plane.width());
    m_Rect.setHeight( Plane.height());
    m_Rect.moveTo(XPlane,YPlane);

    m_Rect.setWidth( Plane1.width());
    m_Rect.setHeight( Plane1.height());
    m_Rect.moveTo(XPlane1,YPlane1);

    m_Rect.setWidth( Plane2.width());
    m_Rect.setHeight( Plane2.height());
    m_Rect.moveTo(XPlane2,YPlane2);

    m_Rect.setWidth( Plane3.width());
    m_Rect.setHeight( Plane3.height());
    m_Rect.moveTo(XPlane3,YPlane3);

    m_Rect.setWidth( Plane4.width());
    m_Rect.setHeight( Plane4.height());
    m_Rect.moveTo(XPlane4,YPlane4);

    m_Rect.setWidth( m_Jianting.width());
    m_Rect.setHeight( m_Jianting.height());
    m_Rect.moveTo(m_XJianting,m_YJianting);

}

void Move::updatePosition(){
    if(m_XJianting>303 )//(1110,520)
    {
          m_XJianting-= 0.5;
//          m_YJianting+=0.1;
    }
    if(m_Plane_Free==0){
        XPlane4+=1;
        if(XPlane4>380&&YPlane4>220) YPlane4-=1;
        if(XPlane4>870) m_Plane_Free=1;

        XPlane3+=1;
        if(XPlane3>320&&YPlane3<350)
        YPlane3+=0.5;
        if(XPlane3>840)
        m_Plane_Free=1;

        XPlane2+=1;
        if(XPlane2>340&&YPlane2>260) YPlane2-=1;
        if(XPlane2>810) m_Plane_Free=1;

        XPlane1+=1;
        if(XPlane1>300&&YPlane1<320) YPlane1+=0.5;
        if(XPlane1>780) m_Plane_Free=1;

        XPlane+=1;
        if(XPlane>750) m_Plane_Free=1;
    }else if(m_Plane_Free==1){
        XPlane4-=1;
        Plane4.load(PLANE);
        XPlane3-=1;
        Plane3.load(PLANE);
        XPlane2-=1;
        Plane2.load(PLANE);
        XPlane1-=1;
        Plane1.load(PLANE);
        XPlane-=1;
        Plane.load(PLANE);
        if(YPlane2<340) YPlane2+=1;
        if(YPlane4<370) YPlane4+=1;
    }
}



void Move::shoot()
{
    //累加时间间隔记录变量
    m_recorder++;
    //判断如果记录数字 未达到发射间隔，直接return
    if(m_recorder < 290)
    {
        return;
    }
        if(missile.m_Free)
        { //将改子弹空闲状态改为假
            missile.m_Free = false;
            //设置发射的子弹坐标
            missile.m_X =320;
            missile.m_Y =300;
        }
}
void Move::shoot1()
{
    //累加时间间隔记录变量
    m_recorder++;
    //判断如果记录数字 未达到发射间隔，直接return
    if(m_recorder < 310)
    {
        return;
   }
    if(missile1.m_Free)
    { //将改子弹空闲状态改为假
        missile1.m_Free = false;
        //设置发射的子弹坐标
        missile1.m_X1 =340;
        missile1.m_Y1 =320;
        //break;
    }
}
void Move::shoot2()
{
    m_recorder++;
    if(m_recorder < 330)
    {
        return;
   }
    if(missile2.m_Free)
    {
        missile2.m_Free = false;
        missile2.m_X2 =380;
        missile2.m_Y2 =260;
    }
}

void Move::shoot3()
{
    m_recorder++;
    if(m_recorder < 350)
    {
        return;
   }
    if(missile3.m_Free)
    {
        missile3.m_Free = false;
        missile3.m_X3 =420;
        missile3.m_Y3 =350;
    }
}

void Move::shoot4()
{
    m_recorder++;
    if(m_recorder < 330)
    {
        return;
   }
    if(missile4.m_Free)
    {
        missile4.m_Free = false;
        missile4.m_X4 =460;
        missile4.m_Y4 =220;
    }
}

void Move::shoot5()
{
    m_recorder++;
    if(m_recorder < 700)
    {
        return;
   }
    if(m_lanjiedan1.m_Free)
    {
        m_lanjiedan1.m_Free = false;
        m_lanjiedan1.m_x1 =900;
        m_lanjiedan1.m_y1 =562;
    }
}

void Move::shoot6()
{
    m_recorder++;
    if(m_recorder < 700){
        return;
   }
    if(m_lanjiedan2.m_Free)
    {
        m_lanjiedan2.m_Free = false;
        m_lanjiedan2.m_x2 =900;
        m_lanjiedan2.m_y2 =562;
    }
}
















