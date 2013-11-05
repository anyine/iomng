Ext.define('VMS.controller.InnerPersonRecordCtrl', {
	extend : 'Ext.app.Controller',
	views : [ 'innerPersonRecord.List'],
	stores : [ 'InnerPersonRecordStore' ],
	models : [ 'InnerPersonRecord' ],

	init : function() {
		console.log('innerperson record controller init');
		this.control();
	}
});