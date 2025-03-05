from odoo import _,models, fields, api

class ExpenseRequest(models.Model):
    _name = 'sale.orders'
    _description = 'Custom Sale Orders'
    _inherit = ['mail.thread','mail.activity.mixin']

    name = fields.Char(string="Customer Name", required=True)
    amount = fields.Float(string="Amount", default= 0.00)
    discount = fields.Float(string="Discount (%)", default=0.00)
    final_price = fields.Float(string="Final Price", compute="_apply_discount", store=True)

    @api.depends('amount', 'discount')
    def _apply_discount(self):
        for record in self:
            total_discount = record.amount * record.discount / 100
            record.final_price = record.amount - total_discount

    @api.constrains('discount')
    def _check_discount(self):
        if self.discount < 0 or self.discount >100:
            self.discount = 0

    def send_message(self):
        self.sudo().message_post(body=_('Somebody: %s is posting an illegal discount: %s')% (self.name,self.discount))



