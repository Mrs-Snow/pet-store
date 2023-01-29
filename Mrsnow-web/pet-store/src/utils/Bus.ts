// //先定义一个类型,emit作为发布
// import { string } from "vue-types"


// //name是事件名称,callback是一个回调函数
// type BusClass = {
//     emit:(name:string)=>void
//     on:(name:string, callback:Function)=>void
// }

// type PramsKey = string|number|symbol


// type List = {
//     [key:PramsKey]: Array<Function>
// }

// class Bus implements BusClass{
//     list: List
//     constructor (){
//         this.list={}
//     }
    

//     emit: (name: string,...args:Array<any>) {
//         //获取list里的数据
//         let eventName:Array<Function> = this.list[name]
//         eventName.forEach(fn=>{
//             fn.apply(this,args)
//         })
//     }

//     on (name:string,callback:Function){
//         //如果是第一次注册则为空,如果被多次注册,则采用之前注册的名字
//         let fn:Array<Function> = this.list[name] || []
//         fn.push(callback)
//         this.list[name] = fn
//     }
// }

// export default new Bus()