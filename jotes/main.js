const {app, BrowserWindow} = require('electron')
const{ipcMain}=require('electron')

//使用模板引擎，路径包文件
  
const template=require('art-template')
const path=require('path')
 
var fs=require('fs')
//让mainWindow对象保持全局引用

let mainWindow

function createWindow () {
  mainWindow = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      nodeIntegration: true,
      preload: path.join(__dirname, 'preload.js')
      //_dirname说明使用绝对文件路径
      //指定将在页面中运行其他脚本之前加载的脚本。
      //无论节点集成是打开还是关闭，这个脚本都可以访问节点api。这个值应该是脚本的绝对文件路径。
      //当关闭节点集成时，预加载脚本可以将节点全局符号重新引入全局范围。
    }
  })

  
  mainWindow.loadFile("pages/Login.html")
  //加载index网页
 

  // Open the DevTools.通过这一句可以直接打开V8的渲染进程调试工具
  // mainWindow.webContents.openDevTools()

  //当窗口关闭时执行函数
  mainWindow.on('closed', function () {
    // Dereference（废弃） the window object, usually you would store windows
    // in an array if your app supports multi windows, this is the time
    // when you should delete the corresponding element.
    //取消对窗口对象的引用，通常会存储窗口
    //如果你的应用程序支持多窗口，你应该在这里删除相应的元素。
    mainWindow = null
  })
}

//下面不需要动
// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
//这个方法将在electron完成时被调用
//初始化，准备创建浏览器窗口。
//有些api只能在此事件发生后使用。
app.on('ready', createWindow)

// Quit when all windows are closed.当所有窗口关闭时退出
app.on('window-all-closed', function () {
  // On macOS it is common for applications and their menu bar
  // to stay active until the user quits explicitly with Cmd  + Q
  //在macOS中保持窗口的活动状态直到用户使用Cmd  + Q退出
  if (process.platform !== 'darwin') app.quit()
})

app.on('activate', function () {
  // On macOS it's common to re-create a window in the app when the
  // dock icon is clicked and there are no other windows open.
  if (mainWindow === null) createWindow()
})

// In this file you can include the rest of your app's specific main process
// code. You can also put them in separate files and require them here.
//你可以在这里写入剩余的主进程，或者引入其它文件中的主进程
ipcMain.on('asynchronous-message', (event, arg) => {
  console.log("mian1" + arg)  // prints "ping"
  event.sender.send('asynchronous-reply', 'pong')//在main process里向web page发出message
})
