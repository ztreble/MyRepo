// // This file is required by the index.html file and will
// // be executed in the renderer process for that window.
// // No Node.js APIs are available in this process because
// // `nodeIntegration` is turned off. Use `preload.js` to
// // selectively enable features needed in the rendering
// // process.
// function getProcessinfo(){
//     console.log("hello")
// }
// const dragwrapper =document.getElementById("drag_test")
// dragwrapper.addEventListener("drop",(e)=>{
//     const files=e.dataTransfer;
//     if(files && files.length>0){
//         const path=files[0].path;
//         console.log('path:',path);
//     }
// })
const {ipcRenderer} =require('electron') 

function init(){
    ipcRenderer.on('asynchronous-reply',(event,arg)=>{
      alert("web2"+arg)
    })
}

function say_hello(){
    ipcRenderer.send('asynchronous-message','ping')
}