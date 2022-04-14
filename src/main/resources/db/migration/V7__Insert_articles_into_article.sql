INSERT INTO article (unit_id, article, type)
SELECT unit_id, '1.3.4.2.4.1', type
from units
where type = 'RTM';

INSERT INTO article (unit_id, article, type)
SELECT unit_id, '1.3.4.2.4.2', type
from units
where type = 'RTM';

INSERT INTO article (unit_id, article, type)
SELECT unit_id, '1.3.4.2.4.3', type
from units
where type = 'RTM';

INSERT INTO article (unit_id, article, type)
SELECT unit_id, '1.4.5.1.4.5', type
from units
where type = 'KTC';

INSERT INTO article (unit_id, article, type)
SELECT unit_id, '1.4.5.1.4.6', type
from units
where type = 'KTC';

INSERT INTO article (unit_id, article, type)
SELECT unit_id, '1.4.5.1.4.7', type
from units
where type = 'KTC';

INSERT INTO article (unit_id, article, type)
SELECT unit_id, '1.4.5.1.4.8', type
from units
where type = 'KTC';


INSERT INTO article (unit_id, article, type)
SELECT unit_id, '1.2.5.2.1.1', type
from units
where type = 'EC';

INSERT INTO article (unit_id, article, type)
SELECT unit_id, '1.2.5.2.1.2', type
from units
where type = 'EC';

INSERT INTO article (unit_id, article, type)
SELECT unit_id, '1.2.5.2.1.3', type
from units
where type = 'EC';