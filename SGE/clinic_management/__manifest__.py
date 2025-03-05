# -*- coding: utf-8 -*-
{
    'name': "clinic_management",

    'summary': "App manejo de clínica",

    'description': """
App para hacer lo necesario con una clínica
    """,

    'author': "Marco",
    'website': "https://www.yourcompany.com",

    # Categories can be used to filter modules in modules listing
    # Check https://github.com/odoo/odoo/blob/15.0/odoo/addons/base/data/ir_module_category_data.xml
    # for the full list
    'category': 'Healthcare',
    'version': '0.1',

    # any module necessary for this one to work correctly
    'depends': ['base'],

    # always loaded
    'data': [
        # 'security/ir.model.access.csv',
        'views/doctor_views.xml',
        'views/patient_views.xml',
        'views/appoinment_views.xml',
        'views/menu.xml',
    ],
    'installable':True,
    'application':True,
    # only loaded in demonstration mode
    'demo': [
        'demo/demo.xml',
    ],
}

