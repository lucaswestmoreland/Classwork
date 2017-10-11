namespace p1
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.button1 = new System.Windows.Forms.Button();
            this.quantity = new System.Windows.Forms.TextBox();
            this.price = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.itemName = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.list = new System.Windows.Forms.ListView();
            this.columnHeader1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader3 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.button2 = new System.Windows.Forms.Button();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.subValue = new System.Windows.Forms.Label();
            this.taxValue = new System.Windows.Forms.Label();
            this.totalValue = new System.Windows.Forms.Label();
            this.ErrorLabel = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(392, 514);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(103, 45);
            this.button1.TabIndex = 0;
            this.button1.Text = "Add to List";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // quantity
            // 
            this.quantity.Font = new System.Drawing.Font("Microsoft Sans Serif", 16F);
            this.quantity.Location = new System.Drawing.Point(12, 447);
            this.quantity.Name = "quantity";
            this.quantity.Size = new System.Drawing.Size(158, 32);
            this.quantity.TabIndex = 1;
            this.quantity.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
            // 
            // price
            // 
            this.price.Font = new System.Drawing.Font("Microsoft Sans Serif", 16F);
            this.price.Location = new System.Drawing.Point(12, 527);
            this.price.Name = "price";
            this.price.Size = new System.Drawing.Size(158, 32);
            this.price.TabIndex = 2;
            this.price.TextChanged += new System.EventHandler(this.textBox2_TextChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 16F);
            this.label1.Location = new System.Drawing.Point(10, 333);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(126, 26);
            this.label1.TabIndex = 3;
            this.label1.Text = "Item Name:";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 16F);
            this.label2.Location = new System.Drawing.Point(10, 418);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(99, 26);
            this.label2.TabIndex = 4;
            this.label2.Text = "Quantity:";
            this.label2.Click += new System.EventHandler(this.label2_Click);
            // 
            // itemName
            // 
            this.itemName.Font = new System.Drawing.Font("Microsoft Sans Serif", 16F);
            this.itemName.Location = new System.Drawing.Point(12, 362);
            this.itemName.Name = "itemName";
            this.itemName.Size = new System.Drawing.Size(158, 32);
            this.itemName.TabIndex = 5;
            this.itemName.TextChanged += new System.EventHandler(this.textBox3_TextChanged);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 16F);
            this.label3.Location = new System.Drawing.Point(10, 498);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(160, 26);
            this.label3.TabIndex = 6;
            this.label3.Text = "Price (per unit):";
            this.label3.Click += new System.EventHandler(this.label3_Click);
            // 
            // list
            // 
            this.list.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader1,
            this.columnHeader2,
            this.columnHeader3});
            this.list.GridLines = true;
            this.list.Location = new System.Drawing.Point(392, 9);
            this.list.Name = "list";
            this.list.Size = new System.Drawing.Size(310, 499);
            this.list.TabIndex = 7;
            this.list.UseCompatibleStateImageBehavior = false;
            this.list.View = System.Windows.Forms.View.Details;
            // 
            // columnHeader1
            // 
            this.columnHeader1.Text = "Item Name:";
            this.columnHeader1.Width = 117;
            // 
            // columnHeader2
            // 
            this.columnHeader2.Text = "Quantity:";
            this.columnHeader2.Width = 54;
            // 
            // columnHeader3
            // 
            this.columnHeader3.Text = "Price (per unit)";
            this.columnHeader3.Width = 129;
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(599, 514);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(103, 45);
            this.button2.TabIndex = 8;
            this.button2.Text = "Clear List";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F);
            this.label4.Location = new System.Drawing.Point(26, 45);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(122, 31);
            this.label4.TabIndex = 9;
            this.label4.Text = "Subtotal:";
            this.label4.Click += new System.EventHandler(this.label4_Click);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F);
            this.label5.Location = new System.Drawing.Point(81, 133);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(67, 31);
            this.label5.TabIndex = 10;
            this.label5.Text = "Tax:";
            this.label5.Click += new System.EventHandler(this.label5_Click);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F);
            this.label6.Location = new System.Drawing.Point(65, 234);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(83, 31);
            this.label6.TabIndex = 11;
            this.label6.Text = "Total:";
            // 
            // subValue
            // 
            this.subValue.AutoSize = true;
            this.subValue.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F);
            this.subValue.Location = new System.Drawing.Point(179, 52);
            this.subValue.Name = "subValue";
            this.subValue.Size = new System.Drawing.Size(45, 24);
            this.subValue.TabIndex = 12;
            this.subValue.Text = "0.00";
            // 
            // taxValue
            // 
            this.taxValue.AutoSize = true;
            this.taxValue.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F);
            this.taxValue.Location = new System.Drawing.Point(179, 140);
            this.taxValue.Name = "taxValue";
            this.taxValue.Size = new System.Drawing.Size(45, 24);
            this.taxValue.TabIndex = 13;
            this.taxValue.Text = "0.00";
            // 
            // totalValue
            // 
            this.totalValue.AutoSize = true;
            this.totalValue.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F);
            this.totalValue.Location = new System.Drawing.Point(179, 241);
            this.totalValue.Name = "totalValue";
            this.totalValue.Size = new System.Drawing.Size(45, 24);
            this.totalValue.TabIndex = 14;
            this.totalValue.Text = "0.00";
            // 
            // ErrorLabel
            // 
            this.ErrorLabel.AutoSize = true;
            this.ErrorLabel.Location = new System.Drawing.Point(180, 546);
            this.ErrorLabel.Name = "ErrorLabel";
            this.ErrorLabel.Size = new System.Drawing.Size(0, 13);
            this.ErrorLabel.TabIndex = 15;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(714, 571);
            this.Controls.Add(this.ErrorLabel);
            this.Controls.Add(this.totalValue);
            this.Controls.Add(this.taxValue);
            this.Controls.Add(this.subValue);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.list);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.itemName);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.price);
            this.Controls.Add(this.quantity);
            this.Controls.Add(this.button1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox quantity;
        private System.Windows.Forms.TextBox price;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox itemName;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.ListView list;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.ColumnHeader columnHeader1;
        private System.Windows.Forms.ColumnHeader columnHeader2;
        private System.Windows.Forms.ColumnHeader columnHeader3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label subValue;
        private System.Windows.Forms.Label taxValue;
        private System.Windows.Forms.Label totalValue;
        private System.Windows.Forms.Label ErrorLabel;
    }
}

