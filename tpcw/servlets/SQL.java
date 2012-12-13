/*
 * Converted from sql.properties by jop@di.uminho.pt. Original copyright
 * notice as follows:
 */
/*
##############################################################################
# sql.properties for build.xml.
# 2003 by Jan Kiefer, extracted from TPCW_Database.java.
#
# This file is distributed "as is". It comes with no warranty and the 
# author takes no responsibility for the consequences of its use.
#
# For Copyright license see tpcw/servlets/TPCW_Database.java.
##############################################################################
*/

package servlets;

public class SQL extends Loader {
	public static void load() {
		load(SQL.class, "sql.properties", "sql.");
	}
	
	public static String getName="SELECT c_fname,c_lname FROM customer WHERE c_id = ?";

	public static String getBook="SELECT * FROM item,author WHERE item.i_a_id = author.a_id AND i_id = ?";

	public static String getCustomer="SELECT * FROM customer, address, country WHERE customer.c_addr_id = address.addr_id AND address.addr_co_id = country.co_id AND customer.c_uname = ?";

	public static String doSubjectSearch="SELECT * FROM item, author WHERE item.i_a_id = author.a_id AND item.i_subject = ? ORDER BY item.i_title FETCH FIRST 50 ROWS ONLY";

	public static String doTitleSearch="SELECT * FROM item, author WHERE item.i_a_id = author.a_id AND item.i_title LIKE ? ORDER BY item.i_title FETCH FIRST 50 ROWS ONLY";

	public static String doAuthorSearch="SELECT * FROM author, item WHERE author.a_lname LIKE ? AND item.i_a_id = author.a_id ORDER BY item.i_title FETCH FIRST 50 ROWS ONLY";

	public static String getNewProducts="SELECT i_id, i_title, a_fname, a_lname " +
		 "FROM item, author " +
		 "WHERE item.i_a_id = author.a_id " +
		 "AND item.i_subject = ? " +
		 "ORDER BY item.i_pub_date DESC,item.i_title " +
		 "FETCH FIRST 50 ROWS ONLY";

	public static String getBestSellers="SELECT i_id, i_title, a_fname, a_lname " +
		 "FROM item, author, order_line " +
		 "WHERE item.i_id = order_line.ol_i_id " +
		 "AND item.i_a_id = author.a_id " +
		 "AND order_line.ol_o_id > (SELECT MAX(o_id)-3333 FROM orders)" +
		 "AND item.i_subject = ? " +
		 "GROUP BY i_id, i_title, a_fname, a_lname " +
		 "ORDER BY SUM(ol_qty) DESC " +
		 "FETCH FIRST 50 ROWS ONLY ";

	public static String getRelated="SELECT J.i_id,J.i_thumbnail from item I, item J where (I.i_related1 = J.i_id or I.i_related2 = J.i_id or I.i_related3 = J.i_id or I.i_related4 = J.i_id or I.i_related5 = J.i_id) and I.i_id = ?";

	public static String adminUpdate="UPDATE item SET i_cost = ?, i_image = ?, i_thumbnail = ?, i_pub_date = CURRENT_DATE WHERE i_id = ?";
	public static String adminUpdate_related="SELECT ol_i_id " +
		 "FROM orders, order_line " +
		 "WHERE orders.o_id = order_line.ol_o_id " +
		 "AND NOT (order_line.ol_i_id = ?) " +
		 "AND orders.o_c_id IN (SELECT o_c_id " +
 		 "                      FROM orders, order_line " +
		 "                      WHERE orders.o_id = order_line.ol_o_id " +
		 "                      AND orders.o_id > (SELECT MAX(o_id)-10000 FROM orders)" +
		 "                      AND order_line.ol_i_id = ?) " +
		 "GROUP BY ol_i_id " +
		 "ORDER BY SUM(ol_qty) DESC " +
		 "FETCH FIRST 5 ROWS ONLY";
	public static String adminUpdate_related1="UPDATE item SET i_related1 = ?, i_related2 = ?, i_related3 = ?, i_related4 = ?, i_related5 = ? WHERE i_id = ?";

	public static String getUserName="SELECT c_uname FROM customer WHERE c_id = ?";

	public static String getPassword="SELECT c_passwd FROM customer WHERE c_uname = ?";

	public static String getRelated1="SELECT i_related1 FROM item where i_id = ?";

	public static String getMostRecentOrder_id="SELECT o_id " +
		     "FROM customer, orders " +
		     "WHERE customer.c_id = orders.o_c_id " +
		     "AND c_uname = ? " +
		     "ORDER BY o_date, orders.o_id DESC " +
		     "FETCH FIRST 1 ROW ONLY";
	public static String getMostRecentOrder_order="SELECT orders.*, customer.*, " +
		     "  cc_xacts.cx_type, " +
		     "  ship.addr_street1 AS ship_addr_street1, " +
		     "  ship.addr_street2 AS ship_addr_street2, " +
		     "  ship.addr_state AS ship_addr_state, " +
		     "  ship.addr_zip AS ship_addr_zip, " +
		     "  ship_co.co_name AS ship_co_name, " +
		     "  bill.addr_street1 AS bill_addr_street1, " +
		     "  bill.addr_street2 AS bill_addr_street2, " +
		     "  bill.addr_state AS bill_addr_state, " +
		     "  bill.addr_zip AS bill_addr_zip, " +
		     "  bill_co.co_name AS bill_co_name " +
		     "FROM customer, orders, cc_xacts," +
		     "  address AS ship, " +
		     "  country AS ship_co, " +
		     "  address AS bill,  " +
		     "  country AS bill_co " +
		     "WHERE orders.o_id = ? " +
		     "  AND cx_o_id = orders.o_id " +
		     "  AND customer.c_id = orders.o_c_id " +
		     "  AND orders.o_bill_addr_id = bill.addr_id " +
		     "  AND bill.addr_co_id = bill_co.co_id " +
		     "  AND orders.o_ship_addr_id = ship.addr_id " +
		     "  AND ship.addr_co_id = ship_co.co_id " +
		     "  AND orders.o_c_id = customer.c_id";
	public static String getMostRecentOrder_lines="SELECT * " +
		     "FROM order_line, item " +
		     "WHERE ol_o_id = ? " +
		     "AND ol_i_id = i_id";

	public static String createEmptyCart="SELECT COUNT(*) FROM shopping_cart";
	public static String createEmptyCart_insert="INSERT into shopping_cart (sc_id, sc_time) " +
		     "VALUES ((SELECT COUNT(*) FROM shopping_cart)," +
		     "CURRENT TIMESTAMP)";

	public static String addItem="SELECT scl_qty FROM shopping_cart_line WHERE scl_sc_id = ? AND scl_i_id = ?";
	public static String addItem_update="UPDATE shopping_cart_line SET scl_qty = ? WHERE scl_sc_id = ? AND scl_i_id = ?";
	public static String addItem_put="INSERT into shopping_cart_line (scl_sc_id, scl_qty, scl_i_id) VALUES (?,?,?)";

	public static String refreshCart_remove="DELETE FROM shopping_cart_line WHERE scl_sc_id = ? AND scl_i_id = ?";
	public static String refreshCart_update="UPDATE shopping_cart_line SET scl_qty = ? WHERE scl_sc_id = ? AND scl_i_id = ?";

	public static String addRandomItemToCartIfNecessary="SELECT COUNT(*) from shopping_cart_line where scl_sc_id = ?";

	public static String resetCartTime="UPDATE shopping_cart SET sc_time = CURRENT TIMESTAMP WHERE sc_id = ?";

	public static String getCart="SELECT * " +
		 "FROM shopping_cart_line, item " +
		 "WHERE scl_i_id = item.i_id AND scl_sc_id = ?";

	public static String refreshSession="UPDATE customer SET c_login = CURRENT TIMESTAMP, c_expiration = CURRENT TIMESTAMP + 2 HOURS WHERE c_id = ?";

	public static String createNewCustomer="INSERT into customer (c_id, c_uname, c_passwd, c_fname, c_lname, c_addr_id, c_phone, c_email, c_since, c_last_login, c_login, c_expiration, c_discount, c_balance, c_ytd_pmt, c_birthdate, c_data) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static String createNewCustomer_maxId="SELECT max(c_id) FROM customer";

	public static String getCDiscount="SELECT c_discount FROM customer WHERE customer.c_id = ?";

	public static String getCAddrId="SELECT c_addr_id FROM customer WHERE customer.c_id = ?";

	public static String getCAddr="SELECT c_addr_id FROM customer WHERE customer.c_id = ?";

	public static String enterCCXact="INSERT into cc_xacts (cx_o_id, cx_type, cx_num, cx_name, cx_expire, cx_xact_amt, cx_xact_date, cx_co_id) " +
		 "VALUES (?, ?, ?, ?, ?, ?, CURRENT DATE, (SELECT co_id FROM address, country WHERE addr_id = ? AND addr_co_id = co_id))";

	public static String clearCart="DELETE FROM shopping_cart_line WHERE scl_sc_id = ?";

	public static String enterAddress_id="SELECT co_id FROM country WHERE co_name = ?";
	public static String enterAddress_match="SELECT addr_id FROM address " +
		 "WHERE addr_street1 = ? " +
		 "AND addr_street2 = ? " +
		 "AND addr_city = ? " +
		 "AND addr_state = ? " +
		 "AND addr_zip = ? " +
		 "AND addr_co_id = ?";
	public static String enterAddress_insert="INSERT into address (addr_id, addr_street1, addr_street2, addr_city, addr_state, addr_zip, addr_co_id) " +
		     "VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static String enterAddress_maxId="SELECT max(addr_id) FROM address";

	public static String enterOrder_insert="INSERT into orders (o_id, o_c_id, o_date, o_sub_total, " +
		 "o_tax, o_total, o_ship_type, o_ship_date, " +
		 "o_bill_addr_id, o_ship_addr_id, o_status) " +
		 "VALUES (?, ?, CURRENT DATE, ?, 8.25, ?, ?, CURRENT DATE + ? DAYS, ?, ?, 'Pending')";
	public static String enterOrder_maxId="SELECT count(o_id) FROM orders";

	public static String addOrderLine="INSERT into order_line (ol_id, ol_o_id, ol_i_id, ol_qty, ol_discount, ol_comments) " +
		 "VALUES (?, ?, ?, ?, ?, ?)";

	public static String getStock="SELECT i_stock FROM item WHERE i_id = ?";

	public static String setStock="UPDATE item SET i_stock = ? WHERE i_id = ?";

	public static String verifyDBConsistency_custId="SELECT c_id FROM customer";
	public static String verifyDBConsistency_itemId="SELECT i_id FROM item";
	public static String verifyDBConsistency_addrId="SELECT addr_id FROM address";
}
