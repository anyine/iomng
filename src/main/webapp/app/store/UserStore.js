Ext.define('VMS.store.UserStore', {
    extend: 'Ext.data.Store',
    model:'VMS.model.User',
    autoLoad:true,
    proxy:{
    	type:'ajax',
    	api:{
    		create:'user/add',
    		read:'user',
    		update:'user/update',
    		destroy:'user/delete'
    	},
    	reader:'json',
    	writer:{
    		type:'json',
    		writeAllFields :false
    	}
    }
});
