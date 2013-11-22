Ext.define('VMS.view.Content', {
	extend : 'Ext.tab.Panel',
	xtype : 'pageContent',
	closeAction:'destory',
	defaults:{
		bodyPadding:10,
		border:false
	},
	items : [ {
		title:'首页',
		bodyCls:'x-mycontent'
	} ]
});
