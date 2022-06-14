tag @s[tag=markertag1] add markertag2
execute as @s[tag=!markertag1] run function expansion:vehicles/spaceship/markers/summon_markers
execute as @s[tag=markertag2] run function expansion:vehicles/spaceship/markers/kill_markers
