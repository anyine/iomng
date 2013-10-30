Ext.define('VMS.store.OrganizationStore', {
    extend: 'Ext.data.TreeStore',
    fields:[{name:'id',type:'int'},{name:'text',type:'varchar',mapping:'name'}],
    //nodeParam:'parentId',
    root:{
    	id:'0',
    	expanded:true,
    	text:'根机构'
    },
    proxy:{
    	type:'ajax',
    	url:'organization/getRoot',
    	reader:'json'
    }
});
