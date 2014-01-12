Ext.define('VMS.view.Header', {
	extend : 'Ext.Toolbar',
	xtype : 'pageHeader',
	ui : 'sencha',
	cls : 'x-logo',
	height : 53,
	items : [ '->', {
		id : 'changePwd',
		text:"修改密码",
		width : 71,
		height : 27
	} , {
		id : 'logout',
		cls : 'x-logout-button',
		width : 71,
		height : 27
	} ]
});
