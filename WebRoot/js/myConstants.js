
var opertionUrl = "http://localhost:8080/spiderNew/services/"
	
var DUTY_CZ = 0;		//厂长
var DUTY_CJZR = 1;		//车间主任
var DUTY_YSG = 2;		//印刷工
var DUTY_ZDG = 3;		//装订工
var DUTY_KG = 4;		//库管
var DUTY_CN = 5;		//出纳
var DUTY_CG = 6;		//采购

var STATE_PRODUCING = 0;	//生产单状态,待领料
var STATE_FINISHED = 1;		//生产单状态,结束
var STATE_GETPAPER = 2;     //生产单状态,已领料,生产中
var STATE_BAD = 3;          //生产单状态,废除
