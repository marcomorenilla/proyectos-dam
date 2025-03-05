{
    'name': 'Todo App',
    'version': '1.0',
    'author': 'Marco',
    'category': 'Productivity',
    'summary': 'Manage pending tasks',
    'depends': ['base'],
    'data': [
        #'security/ir.model.access.csv', # No arranca la app
        'views/task_view.xml'
    ],
    'installable': True,
    'application': True,
}
