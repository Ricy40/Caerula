tp @s[scores={spaceship_speed=1}] ^ ^ ^0.2
tp @s[scores={spaceship_speed=2}] ^ ^ ^0.4
tp @s[scores={spaceship_speed=3}] ^ ^ ^0.6
tp @s[scores={spaceship_speed=4}] ^ ^ ^0.8
tp @s[scores={spaceship_speed=5}] ^ ^ ^1.0
tp @s[scores={spaceship_speed=6}] ^ ^ ^1.2
tp @s[scores={spaceship_speed=7}] ^ ^ ^1.4
tp @s[scores={spaceship_speed=8}] ^ ^ ^1.6

execute unless block ~ ~ ~ #expansion:expansion_air run tp @s ~ ~1 ~
execute unless block ~ ~1 ~ #expansion:expansion_air run tp @s ~ ~-1 ~

execute if score @s sneak_delay matches 20 if predicate expansion:dimension/space run execute positioned -621 512 126 unless entity @s[distance=..250] run execute positioned -1882 512 -1334 unless entity @s[distance=..250] run execute positioned 1358 512 1741 unless entity @s[distance=..250] run execute positioned 2448 512 -1605 unless entity @s[distance=..250] run execute positioned -2873 512 1536 unless entity @s[distance=..250] run execute positioned -3557 512 1635 unless entity @s[distance=..250] run execute positioned 0 512 0 unless entity @s[distance=..250] unless score @s exp_x matches ..-3750 at @s run function expansion:vehicles/spaceship/propulsion/pulse_speed