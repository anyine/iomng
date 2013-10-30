Ext.Loader.setConfig({
	enabled : true,
	paths : {
		'VMS' : 'app',
		'Ext.ux' : 'ux'
	}
});
Ext.require('VMS.store.OrganizationStore');
Ext.onReady(function() {
	Ext.application({
		name : 'VMS',
		appFolder : 'app',
		autoCreateViewport : true,
		controllers : [ 'Main', 'InnerCarCtrl', 'DoorCtrl', 'CarTypeCtrl','UserCtrl','InnerCarRecordCtrl']
	});
});
