Ext.define('VMS.store.InnerCarRecordStore', {
    extend: 'Ext.data.Store',
    model:'VMS.model.InnerCarRecord',
    autoLoad:true,
    storeId:'innerCarRecordStore',
    proxy:{
    	type:'ajax',
    	api:{
    		read:'record/innerCarRecords'
    	},
    	reader:'json'
    }
});
