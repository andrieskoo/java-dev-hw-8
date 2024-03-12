insert into worker (NAME, BIRTHDAY, LEVEL, SALARY)
VALUES ('Viktor', '2000-01-01', 'Trainee', 980),
       ('Natalia', '2000-01-01', 'Trainee', 880),
       ('Vasyl', '2001-08-01', 'Junior', 1200),
       ('Olga', '2002-11-01', 'Junior', 1300),
       ('Mariia', '2000-08-01', 'Junior', 1250),
       ('Yevgenii', '2010-12-01', 'Trainee', 780),
       ('Andrii', '2000-01-01', 'Senior', 9000),
       ('Anna', '2000-01-01', 'Senior', 9800),
       ('Vladyslav', '2000-09-01', 'Senior', 9080),
       ('Olesia', '2000-01-03', 'Trainee', 960),
       ('Iryna', '2000-02-01', 'Middle', 7980),
       ('Nadiia', '2000-11-01', 'Trainee', 950),
       ('Serhii', '2002-01-01', 'Middle', 6980),
       ('Denys', '2008-01-01', 'Trainee', 980),
       ('Pawlo', '2001-01-01', 'Middle', 8000),
       ('Ludmyla', '2010-11-01', 'Senior', 9980);

insert into client (NAME)
values ('Nadiia'),
       ('Andrii'),
       ('Oksana'),
       ('Tetiana'),
       ('Nastia'),
       ('Olga'),
       ('Makar');


insert into project (client_id, name, start_date, finish_date)
values (1, 'Scada', '2024-01-01', '2024-03-01'),
       (2, 'PLC app', '2024-01-11', '2024-03-21'),
       (3, 'Rocket app', '2024-02-11', '2024-03-31'),
       (1, 'Military app', '2024-02-01', '2027-03-01'),
       (1, 'And app', '2025-02-01', '2026-03-01'),
       (1, 'Sun app', '2025-01-01', '2029-06-21'),
       (4, 'Weather app', '2025-11-01', '2028-10-01'),
       (1, 'Diagram app', '2025-11-21', '2029-03-01'),
       (5, 'Platform 1', '2024-11-01', '2025-12-01'),
       (6, 'Web app', '2025-08-01', '2026-09-01'),
       (7, 'Calculator', '2026-06-01', '2030-06-01'),
       (7, 'App 1', '2025-11-20', '2029-03-09'),
       (4, 'Virus to keel rus', '2026-01-01', '2031-06-05'),
       (2, 'App 2', '2025-04-01', '2028-04-02'),
       (2, 'Web site', '2027-01-01', '2029-03-01'),
       (6, 'Mobile app', '2026-03-01', '2029-06-01'),
       (1, 'CRM for Nasdaqu', '2028-01-28', '2032-08-01');



insert into project_worker (project_id, worker_id)
values (1, 16),
       (1, 3),
       (2, 1),
       (2, 16),
       (3, 3),
       (3, 5),
       (3, 6),
       (3, 7),
       (4, 16),
       (5, 6),
       (5, 16),
       (5, 5),
       (8, 16),
       (8, 15),
       (8, 5),
       (6, 6),
       (7, 1),
       (7, 4),
       (8, 4),
       (8, 7),
       (9, 7),
       (9, 6),
       (10, 5),
       (10, 4),
       (11, 3),
       (12, 6),
       (12, 7);