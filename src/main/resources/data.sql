INSERT INTO contacts (name, surname, birthdate, nickname) VALUES ('José', 'Fernandes', '1987-05-25T00:00:00','Zé');

INSERT INTO phones (residential, cell, message, contact_id) VALUES ('16-33365411', '16-997121241', '16-996064371', 1);

INSERT INTO addresses (street, number, city, type,  contact_id) VALUES ('Rua 9' , 234, 'Araraquara', 'residencial',  1);
INSERT INTO addresses (street, number, city, type,  contact_id) VALUES ('Rua 9' , 235, 'Araraquara', 'comercial',  1);

INSERT INTO emails (email,  contact_id) VALUES ('joelho@gmail.com',  1);

INSERT INTO contact_images (profile_image_path, contact_id) VALUES ('https://avatars.githubusercontent.com/u/8093806?v=4', 1);

INSERT INTO admins (name, password) VALUES ('administrador', '$2a$10$DuBWOpKF72cZy65QSRpG6.FuGawO33h7SILkUxFaFjaxmTnvoOU86');