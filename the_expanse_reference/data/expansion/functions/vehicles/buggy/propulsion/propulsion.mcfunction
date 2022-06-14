execute if score @p buggy_speed matches 1 rotated ~ 0 if block ^ ^1 ^3 #expansion:expansion_air run tp @s ^ ^ ^0.2
execute if score @p buggy_speed matches 2 rotated ~ 0 if block ^ ^1 ^3 #expansion:expansion_air run tp @s ^ ^ ^0.3
execute if score @p buggy_speed matches 3 rotated ~ 0 if block ^ ^1 ^3 #expansion:expansion_air run tp @s ^ ^ ^0.4
execute if score @p buggy_speed matches 4 rotated ~ 0 if block ^ ^1 ^3 #expansion:expansion_air run tp @s ^ ^ ^0.5
execute if score @p buggy_speed matches 5 rotated ~ 0 if block ^ ^1 ^3 #expansion:expansion_air run tp @s ^ ^ ^0.6
execute if score @p buggy_speed matches 6 rotated ~ 0 if block ^ ^1 ^3 #expansion:expansion_air run tp @s ^ ^ ^0.7
execute if score @p buggy_speed matches 7 rotated ~ 0 if block ^ ^1 ^3 #expansion:expansion_air run tp @s ^ ^ ^0.8
execute if score @p buggy_speed matches 8 rotated ~ 0 if block ^ ^1 ^3 #expansion:expansion_air run tp @s ^ ^ ^0.9

# synchronize rotation
data modify entity @s Rotation set from entity @p Rotation

# going up- and downhill
execute rotated ~ 0 unless block ^ ^ ^2 #expansion:expansion_air run tp @s ~ ~1 ~
execute rotated ~ 0 if block ^ ^-1 ^-2 #expansion:expansion_air if block ^ ^-1 ^-1 #expansion:expansion_air if block ^ ^-1 ^ #expansion:expansion_air if block ^ ^-1 ^2 #expansion:expansion_air if block ^ ^-1 ^1 #expansion:expansion_air run tp @s ~ ~-1 ~

# fuel removal
scoreboard players add @s fuel_timer 1
scoreboard players remove @s[scores={fuel_timer=300}] fuel_level 1
scoreboard players set @s[scores={fuel_timer=300..}] fuel_timer 0

# animation
function expansion:vehicles/buggy/wheels_animation