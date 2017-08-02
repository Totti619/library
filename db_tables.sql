/*
drop database library;
create database library;
*/
use library;

/*
create table country (
	id int unsigned primary key not null auto_increment,
    name varchar(50) not null
);
create table person (
	id int unsigned primary key not null auto_increment,
    name varchar(50) not null,
    surnames varchar(50) not null
);
create table subject (
	id int unsigned primary key not null auto_increment,
    name varchar(50) not null
);
create table industry (
	id int unsigned primary key not null auto_increment,
    name varchar(50) not null
);
create table company (
	id int unsigned primary key not null auto_increment,
    name varchar(50) not null,
    number_employees int unsigned not null
);
create table company_industry (
	company int unsigned not null,
    industry int unsigned not null,
    
    primary key company_industry(company, industry),
    constraint fk_company_industry_company foreign key company_industry(company) references company(id) on delete cascade on update cascade,
    constraint fk_company_industry_industry foreign key company_industry(industry) references industry(id) on delete cascade on update cascade
);
create table element (
	id int unsigned primary key not null auto_increment,
    title varchar(200) not null,
    year int unsigned not null,
    stock int unsigned not null,
    country int unsigned not null, 
    
    foreign key element(country) references country(id) on delete cascade on update cascade
);
create table magazine (
	id int unsigned primary key not null,
    isbn bigint(13) unsigned not null,
    publisher int unsigned not null,
    
    constraint fk_id_magazine foreign key magazine(id) references element(id) on delete cascade on update cascade,
	constraint fk_publisher_magazine foreign key magazine(publisher) references company(id) on delete cascade on update cascade
);
create table magazine_founder (
	magazine int unsigned not null,
    founder int unsigned not null,
    
    primary key magazine_founder(magazine, founder),
    constraint fk_magazine_founder_magazine foreign key magazine_founder(magazine) references magazine(id) on delete cascade on update cascade,
    constraint fk_magazine_founder_founder foreign key magazine_founder(founder) references person(id) on delete cascade on update cascade
);
create table book (
	id int unsigned primary key not null,
    isbn bigint(13) unsigned not null,
    edition_number smallint unsigned not null,
    publisher int unsigned not null,
    
    constraint fk_id_book foreign key book(id) references element(id) on delete cascade on update cascade,
	constraint fk_publisher_book foreign key book(publisher) references company(id) on delete cascade on update cascade
);
create table book_author (
	book int unsigned not null,
    author int unsigned not null,
    
    primary key book_author(book, author),
    constraint fk_book_author_book foreign key book_author(book) references book(id) on delete cascade on update cascade,
    constraint fk_book_author_author foreign key book_author(author) references person(id) on delete cascade on update cascade
);
create table movie (
	id int unsigned primary key not null,
    duration tinyint unsigned not null,
    director int unsigned not null,
    
    constraint fk_id_movie foreign key movie(id) references element(id) on delete cascade on update cascade,
    constraint fk_movie_director foreign key movie(director) references person(id) on delete cascade on update cascade
);
create table movie_starred_by (
	movie int unsigned not null,
    protagonist int unsigned not null,
    
    primary key movie_starred_by(movie, protagonist),
    constraint fk_movie_starred_by_movie foreign key movie_starred_by(movie) references movie(id) on delete cascade on update cascade,
    constraint fk_movie_starred_by_protagonist foreign key movie_starred_by(protagonist) references person(id) on delete cascade on update cascade
);
create table movie_scripted_by (
	movie int unsigned not null,
    scripter int unsigned not null,
    
    primary key movie_scripted_by(movie, scripter),
    constraint fk_movie_scripted_by_movie foreign key movie_scripted_by(movie) references movie(id) on delete cascade on update cascade,
    constraint fk_movie_scripted_by_scripter foreign key movie_scripted_by(scripter) references person(id) on delete cascade on update cascade
);
create table movie_produced_by (
	movie int unsigned not null,
    producer int unsigned not null,
    
    primary key movie_produced_by(movie, producer),
    constraint fk_movie_produced_by_movie foreign key movie_produced_by(movie) references movie(id) on delete cascade on update cascade,
    constraint fk_movie_produced_by_producer foreign key movie_produced_by(producer) references person(id) on delete cascade on update cascade
);
create table videogame (
	id int unsigned primary key not null,
    
    constraint fk_id_videogame foreign key videogame(id) references element(id) on delete cascade on update cascade
);
create table videogame_directed_by (
	videogame int unsigned not null,
    director int unsigned not null,
    
    primary key videogame_directed_by(videogame, director),
    constraint fk_videogame_directed_by_videogame foreign key videogame_directed_by(videogame) references videogame(id) on delete cascade on update cascade,
    constraint fk_videogame_directed_by_director foreign key videogame_directed_by(director) references person(id) on delete cascade on update cascade
);
create table videogame_developed_by (
	videogame int unsigned not null,
    developing_company int unsigned not null,
    
    primary key videogame_developed_by(videogame, developing_company),
    constraint fk_videogame_developed_by_videogame foreign key videogame_developed_by(videogame) references videogame(id) on delete cascade on update cascade,
    constraint fk_videogame_developed_by_developing_company foreign key videogame_developed_by(developing_company) references company(id) on delete cascade on update cascade
);
create table videogame_published_by (
	videogame int unsigned not null,
    publishing_company int unsigned not null,
    
    primary key videogame_published_by(videogame, publishing_company),
    constraint fk_videogame_published_by_videogame foreign key videogame_published_by(videogame) references videogame(id) on delete cascade on update cascade,
    constraint fk_videogame_published_by_publishing_company foreign key videogame_published_by(publishing_company) references company(id) on delete cascade on update cascade
);
create table videogame_produced_by (
	videogame int unsigned not null,
    producer int unsigned not null,
    
    primary key videogame_produced_by(videogame, producer),
    constraint fk_videogame_produced_by_videogame foreign key videogame_produced_by(videogame) references videogame(id) on delete cascade on update cascade,
    constraint fk_videogame_produced_by_producer foreign key videogame_produced_by(producer) references person(id) on delete cascade on update cascade
);
create table videogame_designed_by (
	videogame int unsigned not null,
    designer int unsigned not null,
    
    primary key videogame_designed_by(videogame, designer),
    constraint fk_videogame_designed_by_videogame foreign key videogame_designed_by(videogame) references videogame(id) on delete cascade on update cascade,
    constraint fk_videogame_designed_by_designer foreign key videogame_designed_by(designer) references person(id) on delete cascade on update cascade
);
create table videogame_programmed_by (
	videogame int unsigned not null,
    programmer int unsigned not null,
    
    primary key videogame_programmed_by(videogame, programmer),
    constraint fk_videogame_programmed_by_videogame foreign key videogame_programmed_by(videogame) references videogame(id) on delete cascade on update cascade,
    constraint fk_videogame_programmed_by_programmer foreign key videogame_programmed_by(programmer) references person(id) on delete cascade on update cascade
);
create table company_industry (
	company int unsigned not null,
    industry int unsigned not null,
    
    primary key company_industry(company, industry),
    constraint fk_company_industry_company foreign key company_industry(company) references company(id) on delete cascade on update cascade,
    constraint fk_company_industry_industry foreign key company_industry(industry) references industry(id) on delete cascade on update cascade
);
create table element_subject (
	element int unsigned not null,
    subject int unsigned not null,
    
    primary key element_subject(element, subject),
    constraint fk_element_subject_element foreign key element_subject(element) references element(id) on delete cascade on update cascade,
    constraint fk_element_subject_subject foreign key element_subject(subject) references subject(id) on delete cascade on update cascade
);


alter table person add unique(name, surnames);

drop table videogame_designed_by;
drop table videogame_developed_by;
drop table videogame_produced_by;
drop table videogame_programmed_by;
drop table videogame_published_by;
drop table videogame;


delete from country; delete from element; delete from book; delete from book_author; delete from company;
delete from magazine; delete from magazine_founder; delete from industry; delete from company_industry; 
delete from person; delete from movie; delete from movie_produced_by;
delete from movie_scripted_by; delete from movie_starred_by; delete from subject; delete from element_subject;
delete from videogame; delete from videogame_designed_by; delete from videogame_developed_by; delete from videogame_directed_by; delete from videogame_produced_by; delete from videogame_programmed_by; delete from videogame_published_by;
*/

/* -------- TEST QUERIES --------- */

-- get the amount of designers working of every videogame
select element.id, title, count(*) as number_designers
from element
join videogame
on element.id = videogame.id
join videogame_designed_by
on videogame_designed_by.videogame = videogame.id
join person
on videogame_designed_by.designer = person.id
group by element.id
having count(*)
; 