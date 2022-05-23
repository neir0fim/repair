INSERT into units (unit_id, type) values (1, 'RTM');
INSERT INTO article  (unit_id, type, article, id) values (1, 'RTM', '1.3.4.2.4.1', 1);
Insert Into material (cod, name, coddk, uom, value1)  values (
4321343, 'electrodes', '39141000-2', 'kg', 45);
INSERT INTO repair (repair_id, description, article, type)
 VALUES (1, 'pump repair in PNC-3', '1.3.4.2.4.1', 'RTM');
INSERT INTO works (id, cod, name, coddk, uom, valuesOne, amount, repair_id) VALUES (
1, 1232, 'Pipe 420x8 st.3 sp.5', '31100000-7', 'r.m.', 48900, 132, 1
);
INSERT INTO works (id, cod, name, coddk, uom, valuesOne, amount, repair_id) VALUES (
2, 1233, 'Pipe 530x8 st.3 sp.5', '31100000-7', 'r.m.', 4890, 1323, 2
);
