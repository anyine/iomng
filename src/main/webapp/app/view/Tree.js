Ext.define('VMS.view.Tree', {
    extend: 'Ext.tree.Panel',
    xtype: 'treePanel',
    title: '菜单',
    
    rootVisible: false,
    cls: 'examples-list',
    lines: false,
    collapsible : true,
    useArrows: true,
    store: 'TreeStore'
 });