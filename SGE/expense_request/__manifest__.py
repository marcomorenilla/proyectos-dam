{
    'name': 'Expense Request',
    'version': '1.0',
    'author': 'Marco',
    'category': 'Productivity',
    'summary': 'Manage request',
    'depends': ['base'],
    'data': [
        'security/ir.model.access.csv',
        'views/view_expense_request.xml'
    ],
    'installable': True,
    'application': True,
}
