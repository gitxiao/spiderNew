
//获取材料类型名字
function getTypeName(itype){
	var typezhi = 1;
	var typemo = 2;
	var result;
	if(itype == typezhi || itype == typezhi + ""){
		result = "纸";
	}else if(itype == typemo || itype == typemo + ""){
		result = "墨";
	}else{
		result = "其他类型";
	}
	return result;
}

//获取材料状态名
function getMaterialStateName(istate){
	var stateUsing = 0;
	var stateUnusing = 1;
	var result;
	if(istate == stateUnusing || istate == stateUnusing + ""){
		result = "停用";
	}else if(istate == stateUsing || istate == stateUsing + ""){
		result = "在用";
	}else{
		result = "其他";
	}
	return result;
}


//获取生产单状态
function getMakeListStateName(para){
	var ipara = -1;
	if(para){
		ipara = parseInt(para);
	}
	switch(ipara){
	case STATE_PRODUCING:
		return "待领料";
	case STATE_FINISHED:
		return "结束";
	case STATE_GETPAPER:
		return "生产中";
	case STATE_BAD:
		return "废除";
	}
	return "其他";
}

function getDistributeStateName(unAssignedNum,allpagetCount){
	var stateName;
	if(unAssignedNum == allpagetCount){
		stateName = "待分配";
	}else if(unAssignedNum == 0){
		stateName = "分配完毕";
	}else if(unAssignedNum > 0 && unAssignedNum < allpagetCount){
		stateName = "分配中";
	}else{
		stateName = "数据异常";
	}
	return stateName;
}

//获取员工状态名
function getWorkerStateName(para){
	switch(para){
	case 0:
	case "0":
		return "在职";
	case 1:
	case "1":
		return "离职";
	}
	return "暂无信息";
}

//获取员工职位名
function getWorkerDutyName(para){
	switch(para){
	case 0:
	case "0":
		return "厂长";
	case 1:
	case "1":
		return "车间主任";
	case 2:
	case "2":
		return "印刷工";
	case 3:
	case "3":
		return "装订工";
	case 4:
	case "4":
		return "库管";
	case 5:
	case "5":
		return "出纳";
	}
	return "暂无信息";
}

//获取用户类型名
function getUserTypeName(para){
	switch(para){
	case 1:
	case "1":
		return "管理员";
	case 2:
	case "2":
		return "门店";
	case 3:
	case "3":
		return "工厂店";
	case 4:
	case "4":
		return "车间主任";
	case 5:
	case "5":
		return "库管";
	case 6:
	case "6":
		return "厂长";
	case 7:
	case "7":
		return "后台管理员";
	}
	return "暂无信息";
}