INSERT INTO admin (id, username, password)
VALUES (1,'admin','admin');

INSERT INTO categories (id, name, slug)
VALUES (1,'Babies Toys','babies-toys'),
       (2,'Kids Toys','kids-toys'),
       (3,'Grown-ups Toys','grown-ups-toys');

INSERT INTO products (name, slug, description, price, category_id, created_at, updated_at)
VALUES ('Vintage Minature Car','vintage-minature-car','This little car is just so cute, and your baby is going to love driving it around on the floor!',24.99,1,'2020-11-10 10:05:14','2020-11-10 10:05:14'),
       ('Wooden Baby Worm','wooden-baby-worm','A multi-coloured baby worm that will help baby to learn about colours',13.99,1,'2020-11-15 16:52:20','2020-11-15 16:52:21'),
       ('US Style Basketball','us-style-basketball','Traditional NBA basketball that has been imported from the USA',27.99,3,'2020-11-15 16:54:22','2020-11-15 16:54:22'),
       ('Brown Teddy Bear','brown-teddy-bear','Cute brown teddy bear that you can place on the windowsill for baby to stare at',29.99,1,'2020-11-15 16:55:03','2020-11-15 16:55:03'),
       ('Coloured Building Blocks','coloured-building-blocks','Selection of building blocks that come in a wide range of shapes and colours',34.99,1,'2020-11-15 16:56:01','2020-11-15 16:56:01'),
       ('Doctors Play Kit','doctors-play-kit','Get your child to start playing doctors and nurses with this fabulous toy medical kit',37.99,2,'2020-11-15 16:56:48','2020-11-15 16:56:48'),
       ('Alphabet Letters','alphabet-letters','Start baby on their journey to being able to spell properly, with these six letters from A to F',8.99,1,'2020-11-15 16:57:55','2020-11-15 16:57:55'),
       ('Fake Donuts','fake-donuts','Kids will have so much fun pranking their parents with these delicious looking but ultimately fake donuts',16.99,2,'2020-11-15 16:58:27','2020-11-15 16:58:27'),
       ('Rabbit Model','rabbit-model','A not all that realistic looking model of a rabbit, that kids will have fun constructing (and destroying...)',19.99,2,'2020-11-15 16:58:53','2020-11-15 16:58:53'),
       ('PS4 Controller','ps4-controller','Official controller for the Playstation 4. These break easily, so you can never have enough',49.99,3,'2020-11-15 16:59:32','2020-11-15 16:59:32'),
       ('Furry Toys','furry-toys','Collection of furry toys that can sit on the shelf in the babys room and gather dust',38.99,1,'2020-11-15 17:00:31','2020-11-15 17:00:31'),
       ('Hopscotch Kit','hopscotch-kit','Draw a hopscotch kit on the ground outside your house, kids will have loads of fun with this',23.99,2,'2020-11-15 17:01:05','2020-11-15 17:01:05'),
       ('VR Headset','vr-headset','Escape from your boring life into the metaverse, where everything is just more fun.',299.99,3,'2020-11-15 17:01:30','2020-11-15 17:01:30'),
       ('Rubicks Cube','rubicks-cube','The world famous rubicks cube. Bound to keep your kid amused for all of 2 minutes',18.99,2,'2020-11-15 17:02:05','2020-11-15 17:02:05'),
       ('Scrabble Set','scrabble-set','Get their spelling learning back on track with this fabulous Scrabble set',27.99,2,'2020-11-15 17:02:36','2020-11-15 17:02:36'),
       ('Assorted Building Blocks','assorted-building-blocks','Big selection of building blocks that will help baby develop her construction skills',19.99,1,'2020-11-25 07:16:45','2020-11-25 07:16:46'),
       ('Flat screen TV','flat-screen-tv','This TV comes from a presumably reputable brand, and will no doubt provide hours of entertainment',999.99,3,'2020-11-15 17:03:42','2020-11-15 17:03:42'),
       ('Xbox Controller','xbox-controller','Get into some serious two player action with this official xbox controller',49.99,3,'2020-11-15 17:04:13','2020-11-15 17:04:13'),
       ('Darts Board','darts-board','Get all your mates round for a few drinks and throw sharp objects at this darts board',89.99,3,'2020-11-15 17:04:51','2020-11-15 17:04:51'),
       ('Train Toy','train-toy','A wooden train toy that you will no doubt tread on with bare feet at some point',18.99,1,'2020-11-15 17:05:25','2020-11-15 17:05:25'),
       ('Playing Cards','playing-cards','You will never be bored on holiday again as long as you have these playing cards',9.99,2,'2020-11-15 17:05:56','2020-11-15 17:05:56'),
       ('Poker Set','poker-set','Get the lads round for a fun night of gambling with this amazing poker set. Includes chips, cards and instructions.',63.99,3,'2020-11-15 17:06:32','2020-11-15 17:06:32');
