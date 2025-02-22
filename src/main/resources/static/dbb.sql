use loggin_project;

#drop table role_users;
drop table users;
drop table roles;

insert into roles(role_name) value('ROLE_ADMIN');
insert into roles(role_name) value('ROLE_USER');

insert into users(role_id, email, fullname, mobile, password, username) values (
                                                                                1,
                                                                                'admin1@email.com',
                                                                                'admin admin',
                                                                                '77 777 77 77',
                                                                                '{noop}passeradmin1',
                                                                                'admin1'
                                                                               );

insert into users(role_id, email, fullname, mobile, password, username) values (
                                                                                   2,
                                                                                   'user1@email.com',
                                                                                   'user user',
                                                                                   '77 777 77 77',
                                                                                   '{noop}passeruser1',
                                                                                   'user1'
                                                                               );


