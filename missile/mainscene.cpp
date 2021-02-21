#include "mainscene.h"
#include "config.h"
#include "move.h"
#include<iostream>
#include<QIcon>
#include<QPixmap>
#include<QPalette>
#include<QBrush>
#include<QPainter>
#include<QPushButton>
#include<QDebug>
using namespace std;
MainScene::MainScene(QWidget *parent)
    : QWidget(parent)
{
    Map m_map;
    Move m_jianting;
    Move m_feiji;
    Move m_feiji1;
    Move m_feiji2;
    Move m_feiji3;
    Move m_feiji4;
    Missile m_missile;
    Missile m_missile1;
    Missile m_missile2;
    Missile m_missile3;
    Missile m_missile4;
    Missile m_lanjiedan;
    Missile m_lanjiedan1;
    Missile m_lanjiedan2;
    lasttime = 50;
    lanjiedan_bomb[0] = 0;
    lanjiedan_bomb[1] = 0;
    lasttime_lanjie_1 = 50;
    lasttime_lanjie_2 = 50;
    initScene();
}
void MainScene::initScene()
{
    setFixedSize(GAME_WIDTH,GAME_HEIGHT);
    setWindowTitle("无人机群作战");
    JianTingBomb.load(BOMB_PATH);
    m_Timer.setInterval(GAME_RATE);
    MainButton_1=new QPushButton(this);
    MainButton_1->setIcon(QIcon(QUIT_PATH));
    MainButton_1->setIconSize(QSize(150, 70));
    MainButton_1->setGeometry(50,560,120,34);
    connect(MainButton_1,&QPushButton::clicked,this,&MainScene::playGame);
    MainButton_1=new QPushButton(this);
    MainButton_1->setIcon(QIcon(QUIT_PATH1));
//    MainButton_1->setGeometry(50, 50, 100, 50);
    MainButton_1->setIconSize(QSize(150, 70));
    MainButton_1->setGeometry(50,600,120,34);
    connect(MainButton_1,&QPushButton::clicked,this,&MainScene::close);


}
MainScene::~MainScene()
{

}


//碰撞检测逻辑
void MainScene::collisionDetection(){
    if(m_jianting.jianting_life>0){
        if(!bump[0]&&m_jianting.m_XJianting-2<m_feiji.missile.m_X&&m_feiji.missile.m_X<m_jianting.m_XJianting+2){
            qDebug()<<"子弹1命中！坐标为："+QString::number(m_feiji.missile.m_X);
            m_jianting.jianting_life-=500;
            bump[0]=true;
            m_bombs[0].m_Free = false;
            m_bombs[0].m_X = m_feiji.missile.m_X;
            m_bombs[0].m_Y = m_feiji.missile.m_Y;
        }

        if(!bump[1]&&m_jianting.m_XJianting-2<m_feiji1.missile1.m_X1&&m_feiji1.missile1.m_X1<m_jianting.m_XJianting+2){
            qDebug()<<"子弹2命中！坐标为："+QString::number(m_jianting.m_XJianting);
            m_jianting.jianting_life-=500;
            bump[1]=true;
            m_bombs[1].m_Free = false;
            m_bombs[1].m_X = m_feiji1.missile1.m_X1;
            m_bombs[1].m_Y = m_feiji1.missile1.m_Y1;
        }
        if(!bump[2]&&m_jianting.m_XJianting-2<m_feiji2.missile2.m_X2&&m_feiji2.missile2.m_X2<m_jianting.m_XJianting+2){
                        qDebug()<<"子弹3命中！坐标为："+QString::number(m_feiji2.missile2.m_X2);
                        m_jianting.jianting_life-=500;
                        bump[2]=true;
                        m_bombs[2].m_Free = false;
                        m_bombs[2].m_X = m_feiji2.missile2.m_X2;
                        m_bombs[2].m_Y = m_feiji2.missile2.m_Y2;
                    }
        if(!bump[3]&&m_jianting.m_XJianting-2<m_feiji3.missile3.m_X3&&m_feiji3.missile3.m_X3<m_jianting.m_XJianting+2){
            qDebug()<<"子弹4命中！坐标为："+QString::number(m_feiji3.missile3.m_X);
            m_jianting.jianting_life-=500;
            bump[3]=true;
            m_bombs[3].m_Free = false;
            m_bombs[3].m_X = m_feiji3.missile3.m_X3;
            m_bombs[3].m_Y = m_feiji3.missile3.m_Y3;
        }
        if(!bump[4]&&m_jianting.m_YJianting-2<m_feiji4.missile4.m_Y4&&m_feiji4.missile4.m_Y4<m_jianting.m_YJianting+2){
            qDebug()<<"子弹5命中！坐标为："+QString::number(m_feiji4.missile4.m_X);
            m_jianting.jianting_life-=500;
            bump[4]=true;
            m_bombs[4].m_Free = false;
            m_bombs[4].m_X = m_feiji4.missile4.m_X3;
            m_bombs[4].m_Y = m_feiji4.missile4.m_Y3;
        }

        if(m_jianting.m_lanjiedan1.m_x1<=m_feiji1.missile1.m_X1){
            qDebug()<<"change the boolen of 2"<<m_jianting.m_lanjiedan1.m_x1<<m_feiji1.missile1.m_X1;
            bump[1] = true;
            lanjiedan_bomb[0]=true;
        }
        if(m_jianting.m_lanjiedan2.m_x2<=m_feiji3.missile3.m_X3){
            qDebug()<<"change the boolen of 1"<<m_jianting.m_lanjiedan2.m_x2<<m_feiji3.missile3.m_X3;

            bump[3] = true;
            lanjiedan_bomb[1] = true;
        }
        //    else if(m_jianting.m_lanjiedan2.m_x2<=m_feiji3.missile4.m_X3){
        //        if(lanjiedan_bomb[1]&&lasttime_lanjie_1){
        //            lasttime_lanjie_2--;
        //            painter.drawPixmap(m_jianting.m_lanjiedan2.m_x2,m_jianting.m_lanjiedan2.m_y2,JianTingBomb);
        //        }
        //    }


    }
//    for(int k = 0 ; k < BOMB_NUM;k++)
//        {
//            if(m_bombs[k].m_Free)
//                m_bombs[k].m_Free = false;
//                m_bombs[k].m_X = 660;
//                m_bombs[k].m_Y = 370;
//                break;
//    }
}

void MainScene::playGame(){
    m_Timer.start();
    connect(&m_Timer,&QTimer::timeout,[=](){
        updatePosition();
        update();
        time+=0.01;
        collisionDetection();
    });
}


void MainScene::updatePosition(){
    //更新地图坐标
    m_jianting.m_Free = false;
    m_jianting.updatePosition();
    m_feiji.m_Free = false;
    m_feiji.updatePosition();
    m_feiji1.m_Free = false;
    m_feiji1.updatePosition();
    m_feiji2.m_Free = false;
    m_feiji2.updatePosition();
    m_feiji3.m_Free = false;
    m_feiji3.updatePosition();
    m_feiji4.m_Free = false;
    m_feiji4.updatePosition();
    m_lanjiedan1.m_Free = false;
    m_lanjiedan1.updatePosition();
    m_lanjiedan2.m_Free = false;
    m_lanjiedan2.updatePosition();
    //发射导弹
    m_feiji.shoot();
    //如果子弹为非空闲状态，计算发射位置
    if(!m_feiji.missile.m_Free) m_feiji.missile.updatePosition();
    m_feiji1.shoot1();
    if(!m_feiji1.missile1.m_Free) m_feiji1.missile1.updatePosition();
    m_feiji2.shoot2();
    if(!m_feiji2.missile2.m_Free) m_feiji2.missile2.updatePosition();
    m_feiji3.shoot3();
    if(!m_feiji3.missile3.m_Free) m_feiji3.missile3.updatePosition();
    m_feiji4.shoot4();
    if(!m_feiji4.missile4.m_Free) m_feiji4.missile4.updatePosition();


    m_jianting.shoot5();
    if(!m_jianting.m_lanjiedan1.m_Free) m_jianting.m_lanjiedan1.updatePosition();
    m_jianting.shoot6();

    if(!m_jianting.m_lanjiedan2.m_Free)m_jianting.m_lanjiedan2.updatePosition();
    //计算爆炸播放的图片
    for(int i = 0 ; i <=BOMB_NUM;i++) {
        if(m_bombs[i].m_Free == false){
            m_bombs[i].updateInfo();
        }
    }

}



void MainScene::paintEvent(QPaintEvent *)
{
    QPainter painter(this);
    QFont f1("楷体",14,QFont::Bold,true);
    painter.setFont(f1);
    painter.setPen(Qt::yellow);
    painter.drawText(600,500,"舰艇血量：");
    painter.drawText(360,500,tr(gcvt(m_jianting.jianting_life,5,ptr)));
    //绘制地图
    painter.drawPixmap(0,0,1186,721,m_map.m_map1);
    if(m_jianting.jianting_life>0){
    painter.drawPixmap(m_jianting.m_XJianting,m_jianting.m_YJianting,120,50,m_jianting.m_Jianting);}
    else{
        if(lasttime){
            lasttime--;
            painter.drawPixmap(m_jianting.m_XJianting,m_jianting.m_YJianting,120,50,JianTingBomb);
        }
    }
    painter.drawPixmap(m_feiji.XPlane,m_feiji.YPlane,m_feiji.Plane);

    painter.drawPixmap(m_feiji1.XPlane1,m_feiji1.YPlane1,m_feiji1.Plane1);
    painter.drawPixmap(m_feiji2.XPlane2,m_feiji2.YPlane2,m_feiji2.Plane2);
    painter.drawPixmap(m_feiji3.XPlane3,m_feiji3.YPlane3,m_feiji3.Plane3);
    painter.drawPixmap(m_feiji4.XPlane4,m_feiji4.YPlane4,m_feiji4.Plane4);

     //绘制导弹
        if(!m_feiji.missile.m_Free&&!bump[0])
    {
//        if(m_feiji.missile.m_X<m_jianting.m_XJianting+10){
        painter.drawPixmap(m_feiji.missile.m_X,m_feiji.missile.m_Y,m_feiji.missile.m_Missile );
//        }
    }

    if(!m_feiji1.missile1.m_Free&&!bump[1])
    {
//        if(m_feiji1.missile1.m_X1<m_jianting.m_XJianting+10){
        painter.drawPixmap(m_feiji1.missile1.m_X1,m_feiji1.missile1.m_Y1,m_feiji1.missile1.m_Missile );
//        }
    }

    if(!m_feiji2.missile2.m_Free&&!bump[2])
    {
//        if(m_feiji2.missile2.m_X2<=m_jianting.m_lanjiedan1.m_x1){
        painter.drawPixmap(m_feiji2.missile2.m_X2,m_feiji2.missile2.m_Y2,m_feiji2.missile2.m_Missile );
//        }
    }
    if(!m_feiji3.missile3.m_Free&&!bump[3])
    {
//        if(m_feiji3.missile3.m_X3<1050){
        painter.drawPixmap(m_feiji3.missile3.m_X3,m_feiji3.missile3.m_Y3,m_feiji3.missile3.m_Missile );
//        }
    }
    if(!m_feiji4.missile4.m_Free&&!bump[4])
    {
//        if(m_feiji4.missile4.m_X4<=m_jianting.m_lanjiedan2.m_x2){
        painter.drawPixmap(m_feiji4.missile4.m_X4,m_feiji4.missile4.m_Y4,m_feiji4.missile4.m_Missile );
//    }
    }
    if(!m_jianting.m_lanjiedan1.m_Free&&!lanjiedan_bomb[0])
    {
        if(m_jianting.m_lanjiedan1.m_x1>=m_feiji1.missile1.m_X1)
            painter.drawPixmap(m_jianting.m_lanjiedan1.m_x1,m_jianting.m_lanjiedan1.m_y1,m_jianting.m_lanjiedan1.m_Missile );

    }
    if(!m_jianting.m_lanjiedan2.m_Free&&!lanjiedan_bomb[1])
    {
    if(m_jianting.m_lanjiedan2.m_x2>=m_feiji3.missile4.m_X3)
        painter.drawPixmap(m_jianting.m_lanjiedan2.m_x2,m_jianting.m_lanjiedan2.m_y2,m_jianting.m_lanjiedan2.m_Missile );

    }

    //绘制爆炸图片
//    for(int i=0;i<1;i++){
//    if(m_bombs[i].m_Free == false){
//        if((m_feiji4.missile4.m_X4-15<m_jianting.m_lanjiedan2.m_x2)&&(m_feiji4.missile4.m_X4+15>m_jianting.m_lanjiedan2.m_x2))
//            painter.drawPixmap(m_bombs[i].m_X,m_bombs[i].m_Y,m_bombs[i].m_pixArr[m_bombs[i].m_index]);
//        if((m_feiji2.missile2.m_X2-15<m_jianting.m_lanjiedan1.m_x1)&&(m_feiji2.missile2.m_X2+30>m_jianting.m_lanjiedan1.m_x1))
//            painter.drawPixmap(m_bombs[i].m_X-50,m_bombs[i].m_Y+40,m_bombs[i].m_pixArr[m_bombs[i].m_index]);
//        if((m_feiji.missile.m_X-20<m_jianting.m_XJianting)&&(m_jianting.m_XJianting<m_feiji.missile.m_X+70))
//            painter.drawPixmap(m_bombs[i].m_X+210,m_bombs[i].m_Y+170,m_bombs[i].m_pixArr[m_bombs[i].m_index]);
//        if((m_feiji1.missile1.m_X1-20<m_jianting.m_XJianting)&&(m_jianting.m_XJianting<m_feiji1.missile1.m_X1+70))
//            painter.drawPixmap(m_bombs[i].m_X+200,m_bombs[i].m_Y+180,m_bombs[i].m_pixArr[m_bombs[i].m_index]);
//        if(m_jianting.m_XJianting-2<m_feiji3.missile3.m_X3&&m_feiji3.missile3.m_X3<m_jianting.m_XJianting+2)
//            painter.drawPixmap(m_bombs[i].m_X+200,m_bombs[i].m_Y+180,m_bombs[i].m_pixArr[m_bombs[i].m_index]);
//        }
//    }
    for(int i=0;i<=4;i++)
        if(m_bombs[i].m_Free == false){
            painter.drawPixmap(m_bombs[i].m_X,m_bombs[i].m_Y,m_bombs[i].m_pixArr[m_bombs[i].m_index]);
            qDebug()<<"爆炸1";
        }


    if(lanjiedan_bomb[0]&&lasttime_lanjie_1){
        lasttime_lanjie_1--;
        qDebug()<<"爆炸2";
        painter.drawPixmap(m_jianting.m_lanjiedan1.m_x1,m_jianting.m_lanjiedan1.m_y1,JianTingBomb);
    }
    if(lanjiedan_bomb[1]&&lasttime_lanjie_2){
        qDebug()<<"爆炸3";
            lasttime_lanjie_2--;
            painter.drawPixmap(m_jianting.m_lanjiedan2.m_x2,m_jianting.m_lanjiedan2.m_y2,JianTingBomb);
    }
}













