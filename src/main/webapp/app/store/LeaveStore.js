Ext.define('VMS.store.LeaveStore', {
    extend: 'Ext.data.Store',
    model:'VMS.model.Leave',
    //autoLoad:true,
    	sorters : [ {
		property : 'createTime',
		direction : 'DESC'
	} ],
    proxy:{
    	type:'ajax',
    	api:{
    		read:'leave'
    	},
    	reader:'json'
    }
});
