- 封装对象之后，C++的布局成本增加了多少？
没有增加。
对于每一个non-inline member function,只会诞生一个函数实例，对于拥有零个或者一个定义的inline function则会在其每一个使用者上产生一个函数实例。
在布局上的主要成本是由virtual带来的，包括：
virtual function机制，用于支持“执行期绑定”和virtual base class机制，用于实现多次出现在继承中的base class，有一个单一而被共享的实例。
- 对象模型有哪些？
简单对象模型：members全部由slot中的指针指向
表格驱动对象模型：有两个指针指向两个表格，第一个表格是data member table，第二个表格是member function table。函数表格内含函数地址。
C++对象模型：
vtbl：每个类产生的指向virtual functions的指针，放在称为vtbl的表格之中
vptr：每个class object产生一个指针，指向相关的virtual table
- C++如何支持多继承的？
使用bptr指向一个表格，表格中存放了父类地址
- c++如何实现多态的？
1.隐式的转换操作
2.虚函数
3.经过dynamic_cast和typeid运算符 if(circle *pc = dynamic_cast<circle*>(ps))
- c++子类对于父类的非虚同名函数，如何处理？
子类将会隐藏父类的同名函数，因此用户无法通过子类调用父类同名函数；同时，在原来的位置上，子类使bptr指向自己的函数，此时通过该实例调用函数会调用子类自己的函数；如果进行对父类的强制类型转换，会将子类裁剪并且暴露父类。
- 什么时候编译器不会加入default constructors?
当这个class含有一个或者一个以上具有无参构造函数的member class objects的时候。而且这个构造函数将会是 implicitly and trivial的。
- 什么叫Bitwise Copy Semantics？
逐位次拷贝，直接对每一个非静态成员进行复制。这会导致指针指向同一个对象。
- 什么时候会发生Bitwise Copy Semantics,什么时候不会?
四种情况下会由编译器生成non-Bitwise Copy Semantics：
1. class内含一个member object而后者的class声明有一个copy constructor时
2. 当class继承一个base class而后者存在一个copy constructor时
3. 当class声明了一个或者多个virtual functions时
4. 当class派生自一个继承串链，其中有一个或者多个virtual base classes时
- 解释一下什么叫NRV优化？
NRV优化是一种编译器层面的优化，
例如：
```
X bar(){
    X xx;
    return xx;
}
可以被优化为：
void bar(X &_result){
    _result.X::X();
    return;
}
```
- 如果不使用参数化列表，对象如何被初始化？
```
Test::Test{
    _name.String::String();
    String temp = String(0);
    _name.String::operator=(temp);
    temp.String::~String();
    _cnt=0;
}
```
- member-wise和bitwise有什么不一样？
bitwise显然更高效，但是以传值方式返回objects的时候，默认是member-wise的，如果更改成bitwise的话（memset和memcpy），就会由于编译器优化（此时vptr会优先被初始化），将会覆盖掉vptr的初始化操作。
