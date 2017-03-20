function fixPhoneNumber(id) {
    layer.open({
        type: 2,
        title:'修改手机号码',
        area: ['70%','70%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/agent/business/updatePhoneNumberUi.html?id='+id
    });
}