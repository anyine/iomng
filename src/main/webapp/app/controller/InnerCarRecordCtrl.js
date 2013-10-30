Ext.define('VMS.controller.InnerCarRecordCtrl', {
	extend : 'Ext.app.Controller',
	views : [ 'innerCarRecord.List'],
	stores : [ 'InnerCarRecordStore' ],
	models : [ 'InnerCarRecord' ],

	init : function() {
		console.log('innercar record controller init');
		this.control();
	}
});