using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace p1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }









        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e) //clear list
        {
            list.Items.Clear();
            subValue.Text = "0.00";
            taxValue.Text = "0.00";
            totalValue.Text = "0.00";

            quantityValue = 0;
            priceValue = 0;
            tax = 0;
            total = 0;
            subtotal = 0;


        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void label4_Click(object sender, EventArgs e)
        {

        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        double quantityValue = 0;
        double priceValue = 0;
        double tax = 0;
        double total = 0;
        double subtotal = 0;

        private void button1_Click(object sender, EventArgs e) //add to list
        {
            String itemName = this.itemName.Text;
            String quantity = this.quantity.Text;
            String price = this.price.Text;

            String[] tableValues = { itemName, quantity, price };

            ErrorLabel.Text = "";

            var listObject = new ListViewItem(tableValues);
            list.Items.Add(listObject);
            try
            {
                quantityValue = Convert.ToDouble(quantity);
                priceValue += Convert.ToDouble(price) * quantityValue;
            }
            catch
            {
                ErrorLabel.Text = "Only input numbers for quantity and price please.";
            }
            
            tax = priceValue * 0.06;
            total = priceValue + tax;


          
            
            subValue.Text = String.Format("({0:N2})", priceValue);
            taxValue.Text = String.Format("({0:N2})", tax);
            totalValue.Text = String.Format("({0:N2})", total);
        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void label7_Click(object sender, EventArgs e) //subtotal
        {

        }

        private void label8_Click(object sender, EventArgs e) //
        {

        }
    }
}
