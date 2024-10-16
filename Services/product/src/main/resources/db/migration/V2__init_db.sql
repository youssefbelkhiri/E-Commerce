-- Inserting data into the category table
INSERT INTO category (id, description, name)
VALUES (nextval('category_seq'), 'Electronic devices and gadgets', 'Electronics'),
       (nextval('category_seq'), 'Books, journals, and magazines', 'Books'),
       (nextval('category_seq'), 'Furniture and home decor', 'Furniture');

-- Inserting data into the product table
INSERT INTO public.product (id, description, name, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Smartphone with 6GB RAM and 128GB storage', 'Smartphone', 100, 599.99,
        (SELECT id FROM category WHERE name = 'Electronics')),
       (nextval('product_seq'), 'Wooden dining table with 6 chairs', 'Dining Table', 20, 299.99,
        (SELECT id FROM category WHERE name = 'Furniture')),
       (nextval('product_seq'), 'Paperback novel by a famous author', 'Novel', 200, 19.99,
        (SELECT id FROM category WHERE name = 'Books'));
