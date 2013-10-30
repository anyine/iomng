Ext.define('VMS.view.Footer', {
    extend: 'Ext.Toolbar',
    xtype : 'pageFooter',
    ui   : 'sencha',
    height: 33,
    items: [
        {
            xtype: 'component',
            cls  : 'x-footer',
            html : 'Copyright © 2013 <a href="http://www.sdhuijin.cn" target="_blank">山东汇金信息科技有限公司</a>'
        }
    ]
});
