execute unless score @s expansion_dim matches 0 run scoreboard players set @s[predicate=expansion:dimension/overworld] expansion_dim 0
execute unless score @s expansion_dim matches 1 run scoreboard players set @s[predicate=expansion:dimension/the_nether] expansion_dim 1
execute unless score @s expansion_dim matches 2 run scoreboard players set @s[predicate=expansion:dimension/the_end] expansion_dim 2
execute unless score @s expansion_dim matches 3 run scoreboard players set @s[predicate=expansion:dimension/space] expansion_dim 3
execute unless score @s expansion_dim matches 4 run scoreboard players set @s[predicate=expansion:dimension/moon] expansion_dim 4
execute unless score @s expansion_dim matches 5 run scoreboard players set @s[predicate=expansion:dimension/mars] expansion_dim 5
execute unless score @s expansion_dim matches 6 run scoreboard players set @s[predicate=expansion:dimension/venus] expansion_dim 6
execute unless score @s expansion_dim matches 7 run scoreboard players set @s[predicate=expansion:dimension/asteroids] expansion_dim 7
#execute unless score @s expansion_dim matches 8 run scoreboard players set @s[predicate=expansion:dimension/mercury] expansion_dim 8
execute unless score @s expansion_dim matches 9 run scoreboard players set @s[predicate=expansion:dimension/jupiter] expansion_dim 9
execute unless score @s expansion_dim matches 10 run scoreboard players set @s[predicate=expansion:dimension/europa] expansion_dim 10
execute unless score @s expansion_dim matches 11 run scoreboard players set @s[predicate=expansion:dimension/storage] expansion_dim 11

execute unless predicate expansion:dimension/storage unless predicate expansion:dimension/overworld unless predicate expansion:dimension/the_nether unless predicate expansion:dimension/the_end unless predicate expansion:dimension/space unless predicate expansion:dimension/moon unless predicate expansion:dimension/mars unless predicate expansion:dimension/venus unless predicate expansion:dimension/asteroids unless predicate expansion:dimension/jupiter unless predicate expansion:dimension/europa run scoreboard players reset @s expansion_dim
