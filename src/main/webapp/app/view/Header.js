Ext.define('VMS.view.Header', {
    extend: 'Ext.Toolbar',
    xtype : 'pageHeader',
    ui   : 'sencha',
    cls  : 'x-logo',
    height: 53,
    items: ['->',
            {
            	cls:'x-logout-button',	
            	width:71,
            	height:27
            }
            ]
});
