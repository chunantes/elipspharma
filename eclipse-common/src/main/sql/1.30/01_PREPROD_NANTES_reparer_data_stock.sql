delete from lignestock;

update mvtstock set approapprouve=true where id in (3086,3088);

update mvtstock set quantite = 5, dateperemption = '2014-11-30 00:00:00' where id = 2411;
update mvtstock set quantite = 1 where id = 2412;


