- 说一下nginx http模块调用流程
worker进程主循环->事件模块->HTTP框架->HTTP处理模块->HTTP过滤模块链表->其它HTTP处理模块
- 说一下nginx的数据结构
  1. ngx_int_t 有符号整型
  2. ngx_unit_t 无符号整型
  3. ngx_str_t 字符串 有两个成员,len和*data分别是长度和指针
  4. ngx_list_t 描述链表
  5. ngx_list_part_s 描述链表节点
  6. ngx_table_elt_t 散列表/键值对
  7. ngx_bug_t 处理大数据
- 如何定义自己的http模块？
  1. 使用ngx_module_t mgx_http_mytest_module;声明这个模块
  2. ngx_module_t中最重要的字段是void* ctx ——指向特定类型模块(ngx_http_module_t)的公共接口  和 ngx_command_t type ——用于处理nginx.conf中的配置项
  3. ngx_command_t的第三个参数指向一个函数，函数签名如下: static char* ngx_http_mytest(ngx_conf_t *cf,ngx_command_t *cmd,void *conf);这个函数中的一个变量clcf->handler指向了在NGX_HTTP_CONTENT_PHASE阶段调用的方法
  4. 
- 说一下ngx_http_module_t模块和ngx_command_t模块有什么作用？
  
  前一个模块用于读取、加载配置模块，它规定了解析配置文件之前、解析后、创建全局配置项、初始化main级别配置项、创建数据结构存储srv级别配置项、创建数据结构存储loc级别配置项、合并二者配置项的方法
  后一个配置项是一个数组，对于每一个模块，都会遍历这个数组找到符合的配置项，ngx_command_t是这样声明的：
  ```c
  struct ngx_command_s{
    //名称，遍历会查找这个名称
    ngx_str_t name;
    //指定配置项可以出现的位置，比如server{}或location{}
    ngx_uint_t type;
    //处理配置的方法指针
    char *(*set)(ngx_conf_t *cf,ngx_command_t *cmd,void* conf);
    //在配置文件中的偏移量
    ngx_uint_t conf;
    //预设的解析方法
    ngx_uint_t offset;
    //读取配置项后的处理方法
    void *post;
  }
  ```