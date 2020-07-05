ALTER TABLE `pessoas` ADD `rua` VARCHAR(100) AFTER `nacionalidade`;
ALTER TABLE `pessoas` ADD `numero` VARCHAR(6);
ALTER TABLE `pessoas` ADD `bairro` VARCHAR(100);
ALTER TABLE `pessoas` ADD `cidade` VARCHAR(50);
ALTER TABLE `pessoas` ADD `uf` VARCHAR(2);
ALTER TABLE `pessoas` ADD `cep` VARCHAR(8);
ALTER TABLE `pessoas` ADD `complemento` VARCHAR(100);

