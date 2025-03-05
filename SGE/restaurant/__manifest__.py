{
    'name': 'Restaurant Management',
    'version': '1.0',
    'author': 'Marco',
    'category': 'Services',
    'summary': 'Manage menu and orders',
    'depends': ['base'],
    'data': [
        'views/restaurant_table_views.xml',
        'views/restaurant_menu_views.xml',
        'views/restaurant_order_views.xml',
        'views/menu.xml'
    ],
    'installable': True,
    'application': True,
}
