Ext.define('VMS.view.organization.Tree', {
    extend: 'Ext.tree.Panel',
    xtype: 'organizationtree',
    title:'机构	',
    requires:['VMS.view.organization.Menu'],
    rootVisible: true,
    cls: 'examples-list',
    lines: false,
    useArrows: true,
    store: 'OrganizationStore'
 });