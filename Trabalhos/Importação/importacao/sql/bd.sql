DROP DATABASE IF EXISTS `dbImportacao`;

CREATE DATABASE dbImportacao;

USE dbImportacao;

DROP TABLE IF EXISTS `tbControleRecebimento`;
CREATE TABLE `tbControleRecebimento` (
  `codconrec` int(11) NOT NULL auto_increment,
  `tiparq` char(3) NOT NULL,
  `numlot` int(7) NOT NULL,
  `dathraprc` datetime, 	
  PRIMARY KEY  (`codconrec`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into tbControleRecebimento(tiparq,numlot,dathraprc) values ('CLI',599,null);
insert into tbControleRecebimento(tiparq,numlot,dathraprc) values ('CTA',644,null);
insert into tbControleRecebimento(tiparq,numlot,dathraprc) values ('PLA',455,null);
insert into tbControleRecebimento(tiparq,numlot,dathraprc) values ('TRA',455,null);

DROP TABLE IF EXISTS `tbCliente`;
CREATE TABLE `tbCliente` (
  `cpfcli` char(11) NOT NULL,
  `nomcli` varchar(30) NOT NULL,
  `endcli` varchar(30) NOT NULL,
  `baicli` varchar(30) NOT NULL,
  `cidcli` varchar(30) NOT NULL,
  `sigest` varchar(2) NOT NULL,
  `datcad` datetime, 	
  PRIMARY KEY  (`cpfcli`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into tbCliente values (45029954876,'teste da silva','rua teste numero teste','jardim antonia','monte carmelo','MG',null);

DROP TABLE IF EXISTS `tbConta`;
CREATE TABLE `tbConta` (
  `nrocta` int(7) NOT NULL,
  `cpfcli` char(11) NOT NULL,
  `vlrlim` double(10,2) NOT NULL, 
  `diaven` int(2) NOT NULL,
  PRIMARY KEY  (`nrocta`),
  CONSTRAINT `fk_cli` FOREIGN KEY (`cpfcli`) REFERENCES `tbCliente` (`cpfcli`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into tbConta values (5508545,45029954876,200,2);

DROP TABLE IF EXISTS `tbPlastico`;
CREATE TABLE `tbPlastico` (
  `nropla` int(7) NOT NULL,
  `nrocta` int(7) NOT NULL,
  `nompla` varchar(30) NOT NULL,
  `cpfpla` char(11) NOT NULL,
  PRIMARY KEY  (`nropla`),
  CONSTRAINT `fk_cta` FOREIGN KEY (`nrocta`) REFERENCES `tbConta` (`nrocta`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `tbTransacao`;
CREATE TABLE `tbTransacao` (
  `nrotra` int(11) NOT NULL auto_increment,
  `nrocta` int(7) NOT NULL,
  `nropla` int(7) NOT NULL,
  `vlrtra` double(10,2) NOT NULL, 
  `dattra` datetime, 	
  `codloj` int(7) NOT NULL,
  PRIMARY KEY  (`nrotra`),
  CONSTRAINT `fk_pla` FOREIGN KEY (`nropla`) REFERENCES `tbPlastico` (`nropla`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;