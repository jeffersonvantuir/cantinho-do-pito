DELIMITER $$
CREATE TRIGGER request_update AFTER UPDATE ON products
FOR EACH ROW
BEGIN
	IF (NEW.stock > 0)
		THEN
		UPDATE requests re
			SET re.status = TRUE
				WHERE re.product_id = NEW.id
				AND re.status = FALSE;
	ELSE
		UPDATE requests re
			SET re.status = FALSE
				WHERE re.product_id = NEW.id
				AND re.status = TRUE;
	END IF;
END;
$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER stock_update AFTER INSERT ON buy_products
FOR EACH ROW
BEGIN
	IF ((SELECT stock FROM products WHERE id=NEW.product_id) >= NEW.amount)
	THEN
		UPDATE products pr
			SET pr.stock = pr.stock - NEW.amount
				WHERE pr.id = NEW.product_id;
	ELSE 
		DELETE FROM buy_products WHERE id = NEW.id;
        UPDATE buy b 
			SET b.total_price = b.total_price - NEW.total_price 
				WHERE id = NEW.buy_id;
		SIGNAL SQLSTATE '02000' SET MESSAGE_TEXT = 'Error';
	END IF;
END;
$$
DELIMITER ;
