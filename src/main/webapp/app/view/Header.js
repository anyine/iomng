Ext.define('VMS.view.Header', {
    extend: 'Ext.Toolbar',
    xtype : 'pageHeader',
    ui   : 'sencha',
    height: 53,
    items: [
        {
            xtype: 'component',
            //添加自定义样式
            cls  : 'x-logo',
            html : '车辆人员出入管理系统'
        }
    ]
});
