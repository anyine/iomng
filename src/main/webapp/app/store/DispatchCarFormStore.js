Ext.define('VMS.store.DispatchCarFormStore', {
    extend: 'Ext.data.Store',
    model:'VMS.model.DispatchCarForm',
    //autoLoad:true,
    proxy:{
    	type:'ajax',
    	api:{
    		read:'dispatchCarForm'
    	},
    	reader:'json'
    }
});
