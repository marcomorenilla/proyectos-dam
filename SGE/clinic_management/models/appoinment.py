
from odoo import models, fields

class ClinicAppointment(models.Model):
    _name = 'clinic.appointment'
    _description = 'Appointment'

    doctor_id = fields.Many2one('clinic.doctor', string="Doctor", required=True)
    patient_id = fields.Many2one('clinic.patient', string="Patient", required=True)
    date = fields.Datetime(string="Appointment Date", required=True)
    status = fields.Selection([
        ('scheduled', 'Scheduled'),
        ('completed', 'Completed'),
        ('canceled', 'Canceled')
    ], string="Status", default='scheduled')
